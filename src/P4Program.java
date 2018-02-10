import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class P4Program{

    LinkedList<Lelement> stackList;
    File input;
    Scanner userInput;
    StackNode head;
    String query;
    QueueNode first, last;
    int size;
    private final int milSec = 1000;
    private final int nanoSec = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    double nodeStackWall, nodeStackCPU;

        class Lelement{
            String word;
            int key;
        }

        class StackNode{
            String data;
            StackNode next;
            int key = size;
        }

        class QueueNode{
        String data;
        QueueNode next;
        int key =size;

    }

    void enQueue(String element){
        if(first == null) {
            first = last = new QueueNode();
            first.data = element;
            first.key = size;
            size++;
        }
        else {
            last = new QueueNode();
            last.data = element;
            last.key = size;
            size++;
            first.next = last;

        }
    }

    void deQueue(){
        if(first == null){
            System.out.println("Queue is empty");
        }
        if(first == last){
            first = last = null;
        }
        else{
            first = first.next;
            last = last.next;
        }
    }

        void push(String element) {
            StackNode pushDown = this.head;
            head = new StackNode();
            head.data = element;
            head.next = pushDown;
            size++;
        }

        void pop() {
            if (head == null) {
                System.out.println("Stack underflow");
                return;
            }
            head = head.next;
            size--;

        }

        void peek() {
            if (head == null) {
                System.out.println("Stack is empty");
                return;
            }
            System.out.println("Stack(Node) \nDATA: " + head.data + "\nKEY: " + head.key);
            System.out.println();
        }

        public void loadLists(){
            fillNodeStack();
            fillLinkedListStack();
            fillNodeQueue();
            System.out.println("Lists loaded, returning to menu...");
            System.out.println("-------------------------------------");
            this.run();

        }

        public void fillNodeStack() {
            if (input == null) {
                System.out.println("Input file not found, please select another input file." + "\nReturning to menu...");
                System.out.println("-------------------------------------");
                this.run();
            }
            try {
                Scanner fileScanner = new Scanner(new FileReader(input));
                System.out.println("Loading simple linked list (Stack)...");
                startWall = System.currentTimeMillis();
                startCPU = System.nanoTime();
                while (fileScanner.hasNext()) {
                    this.push(fileScanner.next());
                }
                fileScanner.close();
                stopWall = System.currentTimeMillis();
                stopCPU = System.nanoTime();
                nodeStackWall = ((double) stopWall - (double) startWall) / milSec;
                nodeStackCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
                size = 0; //CHECK AFTER SEARCHING METHODS ARE COMPLETE
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        public void fillLinkedListStack(){
            stackList = new LinkedList<>();
            if(input == null){
                System.out.println("Input file not found, please select another input file." + "\nReturning to menu...");
                System.out.println("-------------------------------------");
                this.run();
            }
            try {
                Scanner fileScanner = new Scanner(new FileReader(input));
                System.out.println("Loading Java LinkedList (Stack)...");
                startWall = System.currentTimeMillis();
                startCPU = System.nanoTime();
                while(fileScanner.hasNext()){
                    Lelement newElement = new Lelement();
                    newElement.word = fileScanner.next();
                    newElement.key = size;
                    stackList.push(newElement);
                    size++;
                }
                fileScanner.close();
                stopWall = System.currentTimeMillis();
                stopCPU = System.nanoTime();
                nodeStackWall = ((double) stopWall - (double) startWall) / milSec;
                nodeStackCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
                size = 0; //CHECK AFTER SEARCHING METHODS ARE COMPLETE
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }

        public void fillNodeQueue(){
            if (input == null) {
                System.out.println("Input file not found, please select another input file." + "\nReturning to menu...");
                System.out.println("-------------------------------------");
                this.run();
            }
            try {
                Scanner fileScanner = new Scanner(new FileReader(input));
                System.out.println("Loading simple linked list (Queue)...");
                startWall = System.currentTimeMillis();
                startCPU = System.nanoTime();
                while (fileScanner.hasNext()) {
                    this.enQueue(fileScanner.next());
                }
                fileScanner.close();
                stopWall = System.currentTimeMillis();
                stopCPU = System.nanoTime();
                nodeStackWall = ((double) stopWall - (double) startWall) / milSec;
                nodeStackCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
                size = 0; //CHECK AFTER SEARCHING METHODS ARE COMPLETE
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        void searchLists() {
            userInput = new Scanner(System.in);
            System.out.println("Please enter a word to be searched");
            try {
                query = userInput.next();

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
            System.out.println("Searching for word: " + query + "...");
            searchNodeStack();
            searchNodeQueue();
            searchLinkedListStack();
        }

        void searchNodeStack() {
            StackNode temp = new StackNode();
            while (head != null) {
                if (head.data.equalsIgnoreCase(query)) {
                    this.peek();
                    return;
                } else if (!head.data.equalsIgnoreCase(query)) {
                    temp = head;
                    temp.next = head.next;
                    this.pop();
                }
            }
            while (temp != null) {
                try {
                    Scanner fileScanner = new Scanner(new FileReader(input));
                    while (fileScanner.hasNext()) {
                        this.push(fileScanner.next());
                    }
                    fileScanner.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
             if(head == null) {
                 System.out.println("Word not found. \nReturning to menu.");
             }
        }

        void searchNodeQueue() {
            while (first != null) {
                if (first.data.equalsIgnoreCase(query)) {
                    System.out.println("Queue(Node) \nDATA: " + first.data + "\nKEY: " + first.key);
                    System.out.println();
                    return;
                }
                if (!first.data.equalsIgnoreCase(query)) {
                    this.deQueue();
                }
                else if(first == null){
                    System.out.println("Word not found. \nReturning to menu.");
                }
            }

        }

        void searchLinkedListStack() {
            LinkedList temp = new LinkedList();
            temp = stackList;
            while (stackList.getFirst() != null) {
                if (query.equalsIgnoreCase(stackList.getFirst().word)){
                    System.out.println("LinkedList \nDATA: " + stackList.getFirst().word + "\nKEY: " + stackList.getFirst().key);
                    System.out.println();
                    return;
                } else if (!query.equalsIgnoreCase(stackList.getFirst().word)) {
                    this.pop();
                }
            }
            if(head == null) {
                System.out.println("Word not found. \nReturning to menu.");
            }
            stackList = temp;
        }

    public void run(){
        System.out.println("P4 Program:" + "\n(Please type a number 1-7 to access the corresponding menu option)" + "\n1 - Input File" + "\n2 - Load Lists" + "\n3 - Word Search");
        userInput = new Scanner(System.in);
        int menuSelection = userInput.nextInt();
        try {
            if(menuSelection == 1){
                this.getInput();
            }
            if(menuSelection == 2){
                this.loadLists();
            }
            if(menuSelection == 3){
                this.searchLists();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //get input file
    public void getInput(){
        userInput = new Scanner(System.in);
        System.out.println("Please type the name of your file: ");
        try {
            input = new File(userInput.next());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("File name saved, returning to menu...");
        System.out.println("-------------------------------------");
        this.run();

    }

//    void getFileName(){
//        System.out.println(this.input);
//    }

    public void loadStackLL(){

    }

    public void loadQueueNode(){

    }

    public void loadQueueLL(){

    }

    public void loadDLL(){

    }

    public void searchStackNode(){

    }

    public void searchStackLL(){

    }

    public void searchQueueNode(){

    }

    public void searchQueueLL(){

    }

    public void searchDLL(){

    }

    public static void main(String[] args) {
        P4Program program = new P4Program();
        program.run();
    }

}
