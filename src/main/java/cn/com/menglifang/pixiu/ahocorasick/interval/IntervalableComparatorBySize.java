package cn.com.menglifang.pixiu.ahocorasick.interval;

import java.util.Comparator;

public class IntervalableComparatorBySize implements Comparator<Intervalable> {

    public int compare(Intervalable intervalable, Intervalable intervalable2) {
        int comparison = intervalable2.size() - intervalable.size();
        if (comparison == 0) {
            comparison = intervalable.getStart() - intervalable2.getStart();
        }
        return comparison;
    }

}