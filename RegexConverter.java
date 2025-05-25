
import java.util.*;


     public class RegexConverter {
             
       //اسكانر عشان  ناخد الريجيكس من المستخدم 
      static Scanner input = new Scanner(System.in); 


           public static void main(String[] args) {
            System.out.print("Enter regex: ");
            String r = RegexConverter.input.nextLine().trim(); 
                   
                
         //التحقق من الريجيكس لتنفيذ الدالة المناسبة
            if (r.equals("a+b+c")) {
                abc();
                                   }
                    
            else if (r.equals("(a|b)*")) {
                starab();
                                      }
                    
            else if (r.equals("a*")) {
                onlyA();
            
            
            else if (r.equals("ab*")) {
                aFollowedByB();
            }
                    
            else if (r.equals("1*01*1")) {
                oneStarZeroOneStarOne();
            }
            else {
                System.out.println("Unknown regex");
            }
            }
             
             
       // (1*01*1) الميثود المسؤلة عن التعامل مع الريجيكس  
        static void oneStarZeroOneStarOne() {
            System.out.println("Processing 1*01*1");

            DFA dfa = new DFA();
            dfa.add(0, '1', 0);
            dfa.add(0, '0', 1);
            dfa.add(1, '1', 1);
            dfa.add(1, '1', 2);
            dfa.finalState(2);

            System.out.println("DFA:");
            dfa.print();

             //TM المكافئة للريجيكس     
            TM tm = new TM();
            tm.add("q0", '1', "q0", '1', 'R');
            tm.add("q0", '0', "q1", '0', 'R');
            tm.add("q1", '1', "q1", '1', 'R');
            tm.add("q1", '1', "q2", '1', 'R');
            tm.done("q2");

            System.out.println("TM:");
            tm.print();
        }

             //(a|b)* معالجة الريجيكس  
        static void starab() {
            System.out.println("Processing (a|b)*");
            DFA dfa = new DFA();
            dfa.add(0, 'a', 0);
            dfa.add(0, 'b', 0);
            dfa.finalState(0); // a .b اى عدد من 
            System.out.println("DFA:");
            dfa.print();

              
            TM tm = new TM();
            tm.add("q0", 'a', "q0", 'a', 'R');
            tm.add("q0", 'b', "q0", 'b', 'R');
            tm.done("q0");
            System.out.println("TM:");
            tm.print();
        }

             //a+b+c معالجة الريجيكس   
        static void abc() {
            System.out.println("Processing a+b+c");
            DFA dfa = new DFA();
            dfa.add(0, 'a', 1);
            dfa.add(1, 'a', 1);
            dfa.add(1, 'b', 2);
            dfa.add(2, 'b', 2);
            dfa.add(2, 'c', 3);
            dfa.finalState(3); //الحالة النهائية
            System.out.println("DFA:");
            dfa.print();
            TM tm = new TM();
            }


           //a* معالجة الريجيكس   
          static void onlyA() {
            System.out.println("Processing a*");
            DFA dfa = new DFA();
            dfa.add(0, 'a', 0);
            dfa.finalState(0); //الحالة النهائية
            System.out.println("DFA:");
            dfa.print();

            TM tm = new TM();
            tm.add("q0", 'a', "q0", 'a', 'R');
            tm.done("q0");
            System.out.println("TM:");
            tm.print();
           }


        //   ab*  معالجة الريجيكس  
        static void aFollowedByB() {
            System.out.println("Processing ab*");
            DFA dfa = new DFA();
            dfa.add(0, 'a', 1);
            dfa.add(1, 'b', 1);
            dfa.finalState(1);
            System.out.println("DFA:");
            dfa.print();

            TM tm = new TM();
            tm.add("q0", 'a', "q1", 'a', 'R');
            tm.add("q1", 'b', "q1", 'b', 'R');
            tm.done("q1");
            System.out.println("TM:");
            tm.print();
          }


          //DFA الكلاس المسؤل عن    
          static class DFA {
            static class T {
          int from; char c; int to;
          T(int f, char ch, int t) { from=f; c=ch; to=t; }
                    }
                  
            List<T> list = new ArrayList<>();
            Set<Integer> finals = new HashSet<>();

             //اضافة انتقال     
            void add(int f, char ch, int t) { list.add(new T(f,ch,t)); }

             //تحديد الفاينال استات     
            void finalState(int s) { finals.add(s); }

            //طباعة الانتقالات والفاينال استات      
              void print() {
                for (T t : list) {
                System.out.println(t.from + " --" + t.c + "--> " + t.to);
                }
                System.out.println("Final states: " + finals);
               }
          }
             

        //TM الكلاس المسؤل عن      
        static class TM {
                
         static class Rule {
                String from; char read; String to; char write; char dir;
                Rule(String f, char r, String t, char w, char d) {
                from=f; read=r; to=t; write=w; dir=d;
             }  
            }
            List<Rule> rules = new ArrayList<>();
            Set<String> finalStates = new HashSet<>();
            void add(String f, char r, String t, char w, char d) {
                rules.add(new Rule(f,r,t,w,d));
            }

             //تحديد الفاينال استات   
            void done(String s) { finalStates.add(s); }

            //طباعة الفاينال استات TM    
            void print() {
                for (Rule r : rules) {
                    System.out.println(r.from + " " + r.read + " => " + r.to + " " + r.write + " " + r.dir);
                }
                System.out.println("TM Final: " + finalStates);
            }
            
          
            }
            }
