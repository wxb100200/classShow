package com.hz.school.util;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expression;
import com.avaje.ebean.ExpressionFactory;
import com.avaje.ebean.Query;
import com.hz.school.model.BaseEntity;

import javax.persistence.Table;
import java.util.List;

/**
 * 数据库查询工具类
 */
public class EbeanUtil {

    /**
     * 根据Class进行查询，默认带RecordStatus.Valid条件。
     * @param clz 类
     * @param <T> Entity类型
     * @return Query<T>
     */
    public static <T> Query<T> find(Class<T> clz){
        Query<T> q = Ebean.find(clz);
        q.where().eq("IsInactive", "1");
        return q;
    }

    /**
     * 根据Class进行查询，默认带RecordStatus.Valid条件。
     * @param clz 类
     * @param <T> Entity类型
     * @return Query<T>
     */
    public static <T extends BaseEntity> T find(Class<T> clz, Long id){
        T t =  Ebean.find(clz,id);
        if(t!=null && t.getIsInactive().equals("0")){
            throw new RuntimeException("Entity.recordStatus is not Valid: " + t );
        }
        return t;
    }

    /**
     * 根据Class进行查询，默认带RecordStatus.Valid条件。
     * @param clz 类
     * @param <T> Entity类型
     * @return Query<T>
     */
    public static <T extends BaseEntity> T find(Class<T> clz, String id){
        T t =  Ebean.find(clz,id);
        if(t!=null && t.getIsInactive().equals("0")){
            throw new RuntimeException("Entity.recordStatus is not Valid: " + t );
        }
        return t;
    }

    /**
     * 应用UriOptions， 需要对返回页面的字段进行约束的时候，需要应用UriOptions设定
     * @param uriOptions 返回字段设定
     * @param query 查询
     * @return 应用成功，返回true, 否则，false：可能是因为UriOptions为空或者格式不正确
     */
    /*public static boolean applyUriOptions(String uriOptions, Query<?> query) {
        UriOptions options = UriOptions.parse(uriOptions);
        return applyUriOptions(options, query);
    }*/

    /**
     * 应用UriOptions， 需要对返回页面的字段进行约束的时候，需要应用UriOptions设定
     * @param options 返回字段设定
     * @param query 查询
     * @return 应用成功，返回true, 否则，false：可能是因为UriOptions为空或者格式不正确
     */
    /*public static boolean applyUriOptions(UriOptions options, Query<?> query) {
        if (!options.isEmpty()) {
            options.apply(query);
            MarshalOptions.setPathProperties(options.getPathProperties());
            return options.hasPathProperties();
        }
        return false;
    }*/

    /**
     * 一次性设置全部查询参数，将UriInfo中的 sort, firstrow/start, maxrows/limit, where/filter应用到Query中
     * @param query Query
     * @param ui UriInfo
     */
    /*public static  void configQuery(Query query,UriInfo ui){
        AbstractEntityResource.applyQuery(query,ui);
    }*/

    /**
     * n,name : name
     * o,operator: operator， 默认 = ，支持  > < = >= <= <> LIKE ~ IN      AND OR
     * t,type: type, 默认 string, 支持 (b/boolean, by/byte, s/string, i/int, l/long, sh/short, d/double, f/float
     * v:value: value: 对于 IN , AND , OR 来说，value里面必须放数组.其他的都放简单属性。
     *
     * 例如：
     *
     * 完整示例：
     * {name:'a',operator:'=',type:'string',value:false}
     *
     * 简化：
     * {n:'a',value:'xyz'}
     *
     * IN示例：
     * {n:'a', o:'IN', value:['xyz','abc']}
     *
     * 带类型的IN示例
     * {n:'a', o:'IN', t:'i', value:[100,200]}
     *
     * OR:
     * {o:'OR',v:[{n:'a',value:'xyz'},{n:'b',value:'xyz'}]}
     *
     * AND:
     * {o:'AND',v:[
     *          {n:'a',value:'xyz'},
     *          {n:'a',value:'xyz'}
     *      ]}
     *
     * AND 简化示例：
     * [
     *          {n:'a',value:'xyz'},
     *          {n:'a',value:'xyz'}
     * ]
     *
     * 复合示例：
     * {o:'OR',v:[
     *      {n:'a',value:'xyz'},
     *      {n:'b',value:'xyz'},
     *      {o:'AND',v:[
     *          {n:'a',value:'xyz'},
     *          {n:'a',value:'xyz'}
     *      ]}
     * ]}
     *
     *
     * @param query
     * @param whereJson
     */

    /**
     *等于
     * @param propName
     * @param value
     * @return
     */
    public static Expression eq(String propName, Object value){
        return Ebean.getExpressionFactory().eq(propName,value);
    }

    /**
     * 大于
     * @param propName
     * @param value
     * @return
     */
    public static Expression gt(String propName,Object value){
        return Ebean.getExpressionFactory().gt(propName,value);
    }

    /**
     * 大于等于
     * @param propName
     * @param value
     * @return
     */
    public static Expression ge(String propName,Object value){
        return Ebean.getExpressionFactory().ge(propName,value);
    }

    /**
     * 小于等于
     * @param propName
     * @param value
     * @return
     */
    public static Expression le(String propName,Object value){
        return Ebean.getExpressionFactory().le(propName,value);
    }

    /**
     * 小于
     * @param propName
     * @param value
     * @return
     */
    public static Expression lt(String propName,Object value){
        return Ebean.getExpressionFactory().lt(propName,value);
    }

    public static Expression isNull(String propName){
        return Ebean.getExpressionFactory().isNull(propName);
    }

    public static Expression isNotNull(String propName){
        return Ebean.getExpressionFactory().isNotNull(propName);
    }

    /**
     * SQL OR
     * @param exp1
     * @param exps
     * @return
     */
    public static Expression or(Expression exp1,Expression... exps){
        ExpressionFactory factory = Ebean.getExpressionFactory();
        Expression result = exp1;
        for(Expression exp : exps){
            result = factory.or(result, exp);
        }
        return result;
    }

    /**
     * SQL AND
     * @param exp1
     * @param exps
     * @return
     */
    public static Expression and(Expression exp1,Expression... exps){
        ExpressionFactory factory = Ebean.getExpressionFactory();
        Expression result = exp1;
        for(Expression exp : exps){
            result = factory.and(result, exp);
        }
        return result;
    }

    /**
     * SQL NOT
     * @param exp1
     * @return
     */
    public static Expression not(Expression exp1){
        ExpressionFactory factory = Ebean.getExpressionFactory();
        Expression result = factory.not(exp1);
        return result;
    }

    /**
     * 取得某个实体类的数据库名称。根据Table 注解查询.
     * @param entity
     * @return
     */
    public static String getTableName(BaseEntity entity){
        Table annotation = entity.getClass().getAnnotation(Table.class);
        return annotation.name();
    }

    /**
     * 取得某个实体类的数据库名称。根据Table 注解查询.
     * @param cls BaseEntity class
     * @return
     */
    public static  String getTableName(Class<?> cls){
        Table annotation = cls.getAnnotation(Table.class);
        return annotation.name();
    }

    /**
     * 一次性保存一个对象
     * @param entities
     */
    public static void save(BaseEntity... entities){
        for(BaseEntity be : entities) {
            Ebean.save(be);
        }
    }

    /**
     * 一次性保存多个对象
     * @param entities
     */
    public static void save(List<BaseEntity> entities) {
        for(BaseEntity be : entities) {
            Ebean.save(be);
        }
    }
}
