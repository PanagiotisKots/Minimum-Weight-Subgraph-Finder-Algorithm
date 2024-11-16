# Problem Description

You are tasked with solving a problem involving a **weighted directed graph** with `n` nodes, numbered from `0` to `n - 1`. Below is a detailed breakdown of the problem.

---

## Problem Statement

You are given:

1. An integer `n`, representing the number of nodes in the graph.
2. A **2D array** `edges`, where:
   - `edges[i] = [fromi, toi, weighti]` denotes a directed edge from node `fromi` to node `toi` with weight `weighti`.
3. Three distinct integers:
   - `src1`: A source node.
   - `src2`: Another source node.
   - `dest`: A destination node.

### Goal

Return the **minimum weight** of a **subgraph** such that:

1. Both `src1` and `src2` can reach `dest`.
2. The subgraph's weight is the sum of its edge weights.

If it is **not possible** to form such a subgraph, return `-1`.

---

## Examples

### Example 1

![Example 1 Graph](assets/img1.png)

**Input**:  
```plaintext
n = 6
edges = [
  [0, 2, 2], [0, 5, 6], [1, 0, 3],
  [1, 4, 5], [2, 1, 1], [2, 3, 3],
  [2, 3, 4], [3, 4, 2], [4, 5, 1]
]
src1 = 0
src2 = 1
dest = 5
