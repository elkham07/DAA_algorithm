# Assignment 1 – Divide and Conquer Algorithms

##  Learning Goals
- Implement classical **Divide-and-Conquer algorithms** with safe recursion patterns.
- Analyze recurrence relations using **Master Theorem** (3 cases) and **Akra–Bazzi theorem**.
- Verify theoretical results through practical benchmarking.
- Collect metrics (**execution time, recursion depth, number of comparisons/allocated objects**) and present them in a report.

---

##  Algorithms 

### 1. MergeSort (Master Case 2)
- Uses **linear merge**.
- Implements **buffer for memory reuse**.
- Introduces **cut-off for small n** → switches to insertion sort.
- Recurrence:  
  `T(n) = 2T(n/2) + Θ(n)` → `Θ(n log n)` (Master Case 2).

### 2. QuickSort (Randomized)
- **Pivot chosen randomly**.
- Recursion always goes into the **smaller partition**, larger handled iteratively.
- Stack limited to **O(log n)** on average.
- Recurrence:  
  `T(n) = T(k) + T(n-k-1) + Θ(n)` → average `Θ(n log n)`.

### 3. Deterministic Select (Median-of-Medians)
- Splits array into groups of 5.
- Recursively selects pivot as median of medians.
- Recursion only in the required subarray.
- Recurrence:  
  `T(n) ≤ T(n/5) + T(7n/10) + Θ(n)` → `Θ(n)` (Akra–Bazzi).

### 4. Closest Pair of Points (2D)
- Points sorted by **x-coordinate**.
- Recursively divides into two halves.
- In the strip, checks up to 7–8 neighbors in **y-order**.
- Recurrence:  
  `T(n) = 2T(n/2) + Θ(n)` → `Θ(n log n)` (Master Case 2).

---

##  Metrics 
- Execution time (ms).
- Recursion depth.
- Number of comparisons and allocations.
- Results stored in CSV for visualization.

### Planned Charts
- **Time vs n**: asymptotic comparison.
- **Depth vs n**: confirm logarithmic recursion depth.
- Discussion of constant factors: cache, JIT, GC.

---

##  Architecture
- All algorithms inside `org.example` package.
- Metrics support via `Metrics` class.
- Results output in `results/` (CSV + plots).
- Benchmarking via **JMH** (`BenchmarkRunner`).

---

##  GitHub Workflow 
- **main** → only stable releases (`v0.1`, `v1.0`).
- **feature branches**:
    - `feature/mergesort`
    - `feature/quicksort`
    - `feature/select`
    - `feature/closest`
    - `feature/metrics`
    - `feature/bench`

### Commit storyline:
- `init`: maven, junit5, ci, readme
- `feat(metrics)`: counters, depth tracker, CSV writer
- `feat(mergesort)`: baseline + buffer + cutoff + tests
- `feat(quicksort)`: randomized pivot + smaller-first recursion
- `refactor(util)`: partition, swap, shuffle, guards
- `feat(select)`: deterministic select (MoM5) + tests
- `feat(closest)`: divide-and-conquer implementation + tests
- `feat(cli)`: args, run algos, emit CSV
- `bench(jmh)`: select vs sort
- `docs(report)`: master cases & AB intuition, plots
- `fix`: edge cases (duplicates, tiny arrays)
- `release`: v1.0

---

## ✅ Testing 
- **MergeSort / QuickSort**: tested on random and worst-case arrays; recursion depth ≤ `~2*log2(n)`.
- **Select**: compared with `Arrays.sort()[k]` on 100 random tests.
- **Closest Pair**: validated against O(n²) implementation for `n ≤ 2000`.

---

## 📌 Summary
- Theory confirmed by experiments:
    - MergeSort & Closest Pair → `Θ(n log n)`
    - QuickSort → `Θ(n log n)` average with random pivot
    - Deterministic Select → linear `Θ(n)`
- Minor deviations caused by cache, allocations, and JVM optimizations.  
