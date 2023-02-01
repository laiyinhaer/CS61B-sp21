package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends  ArrayDeque<T>{

    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }

    public T max() {
        return max(cmp);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        int index = addOne(nextFirst);
        T max = items[index];

        for(int i = 0; i < size; i++) {
            if (cmp.compare(items[index], max) > 0) {
                max = items[index];
            }
            index = addOne(index);
        }

        return max;
    }
}
