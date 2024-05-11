package com.mert.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;

@Data //
@AllArgsConstructor //

@Builder //default constructor
public class UniqChecker<T> {

    public boolean isUnique(List<T> items, Function<T,String> extractor,String valueToCheck){
        return items.stream()
                .map(extractor)
                .map(String::toLowerCase)
                .noneMatch(existingValue-> existingValue.equalsIgnoreCase(valueToCheck.trim().toLowerCase()));

    }
}
