package sample;

public class TargetClassByExtends extends AbstractBean {
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