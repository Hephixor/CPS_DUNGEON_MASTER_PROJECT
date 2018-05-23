**Service:** Map

**Types:** bool, int, Cell

**Observators:**
- **const** Height: [Map] → int  
- **const** Width: [Map] → int  
- CellNature: [Mat] × int × int → Cell  
    - **pre** CellNature(M,x,y) **requires** 0 ≤ x < Width(M) **and** 0 ≤ y < Height(M)

**Constructors:** 
- init: int × int → [Map]
    - **pre** init(w,h) **requires** 0 < w **and** 0 < h
    
**Operators:** 
- OpenDoor: [Map] × int × int → [Map]
    - **pre** OpenDoor(M,x,y) **requires** CellNature(M,x,y) ∈ {**DNC**, **DWC** }
- CloseDoor: [Map] × int × int → [Map]
    - **pre** CloseDoor(M,x,y) **requires** CellNature(M,x,y) ∈ {**DNO**, **DWO** }

**Observations:**
- [invariant]:
    - T
- [init]:
    - Height(init(h,w)) = h
    - Width(init(h,w)) = w
- [OpenDoor]:
     - CellNature(M,x,y) = **DWC** **implies** C`ellNature(OpenDoor(M,x,y),x,y) = **DW`O**
     - CellNature(M,x,y) = **DNC** **implies** CellNature(OpenDoor(M,x,y),x,y) = **DNO**
     - **forall** u ∈ [0; Width(M)-1] **forall** v ∈ [0; Height(M)-1] (u != x **or** v != y)
          - **implies** CellNature(OpenDoor(M,x,y),u,v) = CellNature(M,u,v)
- [CloseDoor]:
     - similaire pour CloseDoor