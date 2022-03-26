package com.sidorovich.pavel.lw7.service.impl;

import com.sidorovich.pavel.lw7.exception.NegativeNumberException;
import com.sidorovich.pavel.lw7.service.FibonacciService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class FibonacciServiceImpl implements FibonacciService {

    private final int ceil;

    @Override
    public List<Integer> numbers() {
        final List<Integer> numbers = new ArrayList<>();
        final int a = 0;
        final int b = 1;

        numbers.add(a);
        numbers.add(b);

        return next(a, b, ceil, numbers);
    }

    private List<Integer> next(int a, int b, int ceil, List<Integer> numbers) {
        final int next = a + b;
        a = b;
        b = next;

        numbers.add(next);
        if (b >= ceil) {
            return numbers;
        }

        return next(a, b, ceil, numbers);
    }

}
