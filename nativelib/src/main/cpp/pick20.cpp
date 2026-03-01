//
// Created by yanachan on 2026/2/22.
//

#include "pick20.h"
#include <vector>
#include <cstdint>
#include <stdexcept>

static constexpr uint32_t B = 20;
static constexpr uint32_t MASK = (1u << B) - 1u;

std::vector<uint32_t> pack20(const std::vector<uint32_t> &a) {
    for (uint32_t v: a) {
        if (v > 1'000'000) throw std::runtime_error("value out of range");
    }

    const size_t n = a.size();
    const size_t total_bits = n * B;
    const size_t words = (total_bits + 31) / 32;
    std::vector<uint32_t> out(words, 0);
    for (size_t i = 0; i < n; i++) {
        uint64_t v = a[i];
        size_t bit_pos = i * B;
        size_t idx = bit_pos >> 5;
        uint32_t off = bit_pos & 31u;

        uint64_t w = v << off;
        out[idx] |= (uint32_t)w;
        if (off + B > 32) {
            out[idx + 1] |= (uint32_t)(w >> 32);
        }
    }

    return out;
}

std::vector<uint32_t> unpack20(const std::vector<uint32_t>& packed, size_t n) {
    std::vector<uint32_t> a(n);

    for (size_t i = 0; i < n; i++) {
        size_t bit_pos = i * B;
        size_t idx = bit_pos >> 5;
        uint32_t off = bit_pos & 31u;

        uint64_t cur = packed[idx];
        if (off + B > 32) {
            cur |= (uint64_t)packed[idx + 1] << 32;
        }
        a[i] = (uint32_t)((cur >> off) & MASK);
    }
    return a;
}