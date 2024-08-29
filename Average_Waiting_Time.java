class Solution {
    public double averageWaitingTime(int[][] customers) {
        if (customers == null || customers.length <1)
        return 0;

        int lastOrderServedTime = -1;
        int customerServiceTime = 0;
        long totalServiceTime = 0;
        for (int[]customer : customers){
            if (lastOrderServedTime < customer[0]){
                customerServiceTime = customer[1];
            } else {
                customerServiceTime= lastOrderServedTime + customer[1] - customer[0];
            }
            lastOrderServedTime= customer[0] + customerServiceTime;
            totalServiceTime +=customerServiceTime;
        }
        return (double)totalServiceTime / customers.length;       
    }
}