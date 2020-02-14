package com.example.ex1.ex2;



public class Generator {
    private boolean num;
    private boolean abc;
    private boolean ABC;
    private boolean sym;
    private int length;
    private String result;
    private MyRandom random;


    public Generator(boolean num, boolean abc, boolean ABC, boolean sym, int length) {
        this.num = num;
        this.abc = abc;
        this.ABC = ABC;
        this.sym = sym;
        this.length=length;
        random = new MyRandom();
    }

    public String getResult() {
        String flag = isTrue();
        result = firstGen(flag);

        int nextLenght = random.nextNotZero()%length;
        if(nextLenght==0)
            nextLenght=1;
        if(num && flag!="num"){
            result = add("num",nextLenght);
            nextLenght--;
        }
        if(nextLenght==0)
            nextLenght=1;
        if(ABC && flag!="ABC"){
            result=add("ABC",nextLenght);
            nextLenght--;
        }
        if(nextLenght==0)
            nextLenght=1;
        if(abc && flag!="abc") {
            result = add("abc", nextLenght);
            nextLenght--;
        }
        if(nextLenght==0)
            nextLenght=1;
        if(sym && flag!="sym"){
            result=add("sym",nextLenght);
            nextLenght--;
        }
        recorrect();


        return result;
    }
    private String isTrue(){
        if(num)
            return"num";
        if(ABC)
            return"ABC";
        if(abc)
            return"abc";
        if(sym)
            return"sym";
        return null;
    }
    private String firstGen(String flag){
        String s="";
        switch (flag){
            case "num":
                for(int i=0;i<length;i++){
                    s+=numGen(random.nextInt()%9);
                }
                break;
            case "ABC":
                for(int i=0;i<length;i++){
                    s+=ABCGen(random.nextInt()%25);
                }
                break;
            case "abc":
                for(int i=0;i<length;i++){
                    s+=abcGen((random.nextInt()%25));
                }
                break;
            case "sym":
                for(int i=0;i<length;i++){
                    s+=symGen((random.nextInt()%21));
                }
                break;

        }
        return s;

    }
    private String numGen (int i){
        String numbers="0123456789";
        return String.valueOf(numbers.charAt(i));
    }
    private String ABCGen (int i){
        String numbers="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(numbers.charAt(i));
    }
    private String abcGen (int i){
        String numbers="abcdefghijklmnopqrstuvwxyz";
        return String.valueOf(numbers.charAt(i));
    }
    private String symGen (int i){
        String numbers=",.';:!@#$%^&&*()_+-=/*";
        return String.valueOf(numbers.charAt(i));
    }
    private String add(String bool, int maxLength){
        StringBuilder builder = new StringBuilder(result);
        System.out.println(maxLength);
        maxLength=(random.nextNotZero())%maxLength;
        if(maxLength==0)
            maxLength=1;

        switch (bool){
            case "num":
                for(int i=0;i<maxLength;i++){
                    builder.setCharAt((random.nextInt())%result.length(),
                            numGen((random.nextInt()%9)).charAt(0));

                }
                break;
            case "ABC":
                for(int i=0;i<maxLength;i++){
                    builder.setCharAt((random.nextInt())%result.length(),
                            ABCGen((random.nextInt()%25)).charAt(0));

                }
                break;
            case "abc":
                for(int i=0;i<maxLength;i++){
                    builder.setCharAt((random.nextInt())%result.length(),
                            abcGen((random.nextInt()%25)).charAt(0));

                }
                break;
            case "sym":
                for(int i=0;i<maxLength;i++){
                    builder.setCharAt((random.nextInt())%result.length(),
                            symGen((random.nextInt()%21)).charAt(0));
                }
                break;

        }
        return builder.toString();
    }
    private void recorrect(){
        while (true){
            if(abc){
                if(!isEqual(result,"abcdefghijklmnopqrstuvwxyz"))
                {
                    result=add("abc",1);
                    continue;
                }
            }
            if(ABC)
                if(!isEqual(result,"ABCDEFGHIJKLMNOPQRSTUVWXYZ"))
                {
                    result=add("ABC",1);
                    continue;
                }
            if(num)
                if(!isEqual(result,"0123456789"))
                {
                    result=add("num",1);
                    continue;
                }
            if(sym)
                if(!isEqual(result,",.';:!@#$%^&&*()_+-=/*"))
                {
                    result=add("sym",1);
                    continue;
                }
            break;

        }

    }
    private boolean isEqual(String s, String s2){
        for (int i=0;i<s.length();i++){
            for(int j=0;j<s2.length();j++){
                if(s.charAt(i)==s2.charAt(j))
                    return true;
            }
        }
        return false;
    }
}
