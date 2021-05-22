package Messages;

public enum MessageType {
<<<<<<< HEAD
   DIFF("DIFF "), MESS("MESS "), ACKM("ACKM "), 
   LAST("LAST "), OLDM("OLDM "), ENDM("ENDM"), 
   REGI("REGI"), REOK("REOK "), RENO("RENO "), 
   RUOK("RUOK"), IMOK("IMOK "), LIST("LIST"),
   LINB("LINB "), ITEM("ITEM "), MGES("MGES "),
   MDIF("MDIF "), MYOU("MYOU "), NONE("");
   
=======
   DIFF("DIFF"), MESS("MESS"), ACKM("ACKM"), 
   LAST("LAST"), OLDM("OLDM"), ENDM("ENDM"), 
   REGI("REGI"), REOK("REOK"), RENO("RENO"), 
   RUOK("RUOK"), IMOK("IMOK"), LIST("LIST"), 
   LINB("LINB"), ITEM("ITEM"), MGES("MGES"), 
   MDIF("MDIF"), MDOK("MDOK"), MERR("MERR"),
   MGOK("MGOK"), NONE("");

>>>>>>> 4b1dda7a8cb22d3583e35149041440595c487bc1
   String value;

   private MessageType(String value) {
      this.value = value;
   }

   public String getValue() {
      return value;
   }

}
