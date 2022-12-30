package com.putoet.statistics;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Permutator<T> {
    public List<List<T>> permute(List<T> source) {
        final List<List<T>> list = new ArrayList<>();
        permuteHelper(list, new ArrayList<>(), source);
        return list;
    }

    private void permuteHelper(List<List<T>> list, List<T> resultList, List<T> source) {
        if (resultList.size() == source.size()) {
            list.add(new ArrayList<>(resultList));
        } else {
            for (int i = 0; i < source.size(); i++) {
                if (resultList.contains(source.get(i))) {
                    continue;
                }

                resultList.add(source.get(i));
                permuteHelper(list, resultList, source);
                resultList.remove(resultList.size() - 1);
            }
        }
    }

    public List<List<T>> combinations(List<T> data) {
        final List<List<T>> combinations = new ArrayList<>();
        combinations(combinations, data, 0);
        return combinations;
    }

    private void combinations(List<List<T>> combinations, List<T> data, int start) {
        if (start == data.size() - 1)
            return;

        for (int i = start + 1; i < data.size(); i++) {
            combinations.add(List.of(data.get(start), data.get(i)));
        }
        combinations(combinations, data, start + 1);
    }
}