import java.util.*;
import java.io.*;

//BJ B1 4826 Range
public class BJ_B1_4826 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        double beforeW = Double.MAX_VALUE;
        double beforeM = Double.MAX_VALUE;
        double meterSum = 0.0;
        double weightSum = 0.0;
        while(true){
            st = new StringTokenizer(br.readLine());
            double meter = Double.parseDouble(st.nextToken());
            double weight = Double.parseDouble(st.nextToken());
            if(meter == -1.0 && weight == -1.0) break;
            if(meter == 0.0 && weight == 0.0) {
                sb.append(String.format("%.0f", meterSum * beforeW / weightSum));
                sb.append("\n");
                //초기화
                beforeW = Double.MAX_VALUE;
                beforeM = Double.MAX_VALUE;
                meterSum = 0.0;
                weightSum = 0.0;
            }
            else {
                if(weight <= beforeW && beforeM != Double.MAX_VALUE && beforeW != Double.MAX_VALUE){
                    meterSum += meter - beforeM;
                    weightSum += beforeW - weight;
                }
                beforeM = meter;
                beforeW = weight;
            }
        }
        System.out.println(sb.toString());
    }
}
