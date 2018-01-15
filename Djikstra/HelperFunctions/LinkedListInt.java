package HelperFunctions;

public class LinkedListInt
{
   // Create a node class
   static class Node
   {
      // Instance variables
      int data;       // Actual data
      Node next;      // Pointer to next node

      // Constructor
      Node(int num)
      {
         data = num;  // Data is the number
         next = null; // Points to nothing
      }
   }

   // Instance variables
   Node head;
   Node tail;
   int len;

   public LinkedListInt(int param)
   {
      len = 1;                         // Set length to 1        
      head = new Node(param);          // Initialize with head
      tail = head;                     // Tail is same as head
   }

   // Method: Add an integer to the end of the linked list
   public void Add(int param)
   {
      len += 1;                          // Add 1 to length
      Node newnode = new Node(param);    // Create new node
      tail.next = newnode;               // Assign node node as next of tail
      tail = newnode;                    // Tail becomes new node
   }

   // Method: Print contents of linked list
   public void Print()
   {
      Node output = head;
      while (true)
      {
         try
         { 
            System.out.println(output.data);
            output = output.next;
         }
         catch (NullPointerException nullPointer)
         {
            break;
         }
      }
   }
}
