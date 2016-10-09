package c.okami.core.support.hibernate;

import org.hibernate.LockMode;

/**
 * 最顶层通用DAO接口，主要定义DAO基础方法
 */
interface IBasicDomain<K,T> {

    K getId();

    /**
     * 通过ID来得到实体对象
     *
     * @param lock 使用的锁模式
     * @return 该主键对应的实体对象
     */
    T findById(LockMode lock);

    /**
     * 通过ID来得到实体对象
     *
     * @return T
     */
    T findById();


    T loadById();

    /**
     * 通过id load对象
     *
     * @param lock
     * @return
     */
    T loadById(LockMode lock);


    /**
     * 保存实体对象
     */
    T save();

    /**
     * 更新实体
     */
    void update();

    void update(LockMode lock);

    void saveOrUpdate(LockMode lock);

    void saveOrUpdate();

    /**
     * 删除实体
     *
     * @throws Exception
     */
    void delete();

    void delete(LockMode lock);

    /**
     * 根据主键删除指定实体
     */
    void deleteById();

//     void deleteById(ID id, LockOptions lock);


    /**
     * 通过合并的方式更新对象
     */
    void merge();

    /***************************************************************************
     * --------------------------------相关知识--------------------------------*
     *
     * 1、Session的load方法和get方法都是通过给定的ID从数据库中加载一个持久化的对象
     * 区别在于：当数据库不存在于ID对应的记录时，load()方法抛出异常，get()方法返回null
     ***************************************************************************/
    /**
     * 加锁指定的实例
     *
     * @param lockMode
     */
    void lock(LockMode lockMode);

    /**
     * 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
     */
    void flush();

    /**
     * 清空缓存
     * <p/>
     * void
     */
    void clear();


}
