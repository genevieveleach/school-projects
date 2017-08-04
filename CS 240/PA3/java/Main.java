public class Main {
  public static void main(String[] args) {
    Queue<String> names = new Queue<>();
    int passes = 4;

    names.enqueue("Lucien");
    names.enqueue("Bruno");
    names.enqueue("Jaymie");
    names.enqueue("Joey");
    names.enqueue("Mae");
    names.enqueue("Sonia");
    names.enqueue("Ella");
    names.enqueue("Janell");

    HotPotato game = new HotPotato();
    System.out.println(game.play(names, passes));
  }
}
