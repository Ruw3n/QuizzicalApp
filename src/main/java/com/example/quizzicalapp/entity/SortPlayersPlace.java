package com.example.quizzicalapp.entity;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class SortPlayersPlace implements Comparator<Player> {
    /**
     *
     * @param o1
     * @param o2
     * @return descending order
     */
    @Override
    public int compare(Player o1, Player o2) {
        if(o1.getTotalPoints()==o2.getTotalPoints())
            return 0;
        else if(o1.getTotalPoints()<o2.getTotalPoints())
            return 1;
        else
            return -1;
    }

    @Override
    public Comparator<Player> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator<Player> thenComparing(Comparator<? super Player> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public <U> Comparator<Player> thenComparing(Function<? super Player, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Player> thenComparing(Function<? super Player, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator<Player> thenComparingInt(ToIntFunction<? super Player> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator<Player> thenComparingLong(ToLongFunction<? super Player> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<Player> thenComparingDouble(ToDoubleFunction<? super Player> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }
}
