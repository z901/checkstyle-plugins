package sample;

public class TestClass {
  void m() {
    System.out.printf("bad1 %d %d %d", 1, 2,
        3);
    System.out.printf("bad2 %d %d %d",
        1, 2,
        3);
    System.out.printf("bad3-first non-aligned %d %d %d",
        1,
        2,
        3);
    System.out.printf(
        "this is ok %d %d %d",
        1,
        2,
        3);
    System.out.println();
  }
}