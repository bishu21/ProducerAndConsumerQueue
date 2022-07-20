public interface ConcurrencyQueue <T> {
    void push(T data);
    T poll();
}
