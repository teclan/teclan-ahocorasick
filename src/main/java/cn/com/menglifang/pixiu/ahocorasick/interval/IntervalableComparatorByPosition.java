package cn.com.menglifang.pixiu.ahocorasick.interval;

import java.util.Comparator;

public class IntervalableComparatorByPosition
        implements Comparator<Intervalable> {

    public int compare(Intervalable intervalable, Intervalable intervalable2) {
        return intervalable.getStart() - intervalable2.getStart();
    }

}