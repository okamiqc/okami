package c.okami.core.support.cache;
public interface CacheProvider {
    public boolean set(String key, int time, Object o);

    public boolean delete(String key);

    public <T> T get(String key);

}
