public class Main {
    public String solution(int[] numbers) {
        /*
        1의 자리수 끼리 먼저 잰다음에,
        2자리수끼리 재서 그 사이에 끼워 넣고,
        3의 자리수끼리 재서 또 그 사이에 끼워 넣고,
        
        내 숫자보다 그 뒤 숫자 전부가 높으면 앞, 내 숫자보다 그 뒤 숫자 전부가 낮으면 뒤
        
        푸는 방법 확인
        3 -> 3333으로
        34 -> 3433으로 채운다음 비교하기
        
        원래 숫자로 바꾸는 법은..?
        */
        
        String[][] numberAndDigits = new String[numbers.length][2];
        String[] sortedNumber = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            String sNum = String.valueOf(numbers[i]);
            if(sNum.length() == 1){
                numberAndDigits[i][0] = sNum.repeat(4);
                numberAndDigits[i][1] = "1";
            } else if(sNum.length() == 2){
                numberAndDigits[i][0] = sNum + String.valueOf(sNum.charAt(0)).repeat(2);
                numberAndDigits[i][1] = "2";
            } else if(sNum.length() == 3){
                numberAndDigits[i][0] = sNum + String.valueOf(sNum.charAt(0)).repeat(1);
                numberAndDigits[i][1] = "3";
            } else {
                numberAndDigits[i][0] = sNum;
                numberAndDigits[i][1] = "4";
            }
        }
        
        Arrays.sort(numberAndDigits, new Comparator<String[]>(){
            @Override
            public int compare(String[] a, String[] b){
                return a[0].compareTo(b[0]);
            }
        });
        
        for(int i = 0; i < numberAndDigits.length; i++){
            sortedNumber[i] = numberAndDigits[i][0].substring(0, Integer.parseInt(numberAndDigits[i][1]));
        }
        
        StringBuilder answer = new StringBuilder();
        for(int i = sortedNumber.length - 1; i >= 0; i--){
            answer.append(sortedNumber[i]);
        }
        
        return answer.toString();
    }
}
