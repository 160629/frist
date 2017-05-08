package bokeyuan;

public class MinStepToArrayEnd {
	
	/**
     * 根据当前位置的跳动范围返回一个最优的位置，该位置使得连续两次跳动可获得最远距离 如果当前位置可以直接跳出数组范围，则最有位置为当前位置
     * 这里默认传入的参数已经经过验证，满足要求
     * 
     * @param target
     *            目标数组
     * @param currentLocation
     *            当前位置,对应元素的从左到右的排列位置，第一个元素为1，最后一个元素为数组长度
     * @return 最优秀位置
     */
    private static int getBestLocation(int[] target, int currentLocation) {
        // 初始化二连跳的最远位置，可以超出数组范围
        int maxLocation = currentLocation + target[currentLocation - 1];
        // 初始化当前位置
        int bestLocation = currentLocation;
        // 遍历当前位置覆盖的元素，获得二连跳的最远位置和对应的最优位置
        for (int i = 0; i <= target[currentLocation - 1]; i++) {
            int temp = target[(i + currentLocation) - 1] + (i + currentLocation);
            if (temp >= target.length) {
                return i + currentLocation;
            }
            if (temp > maxLocation) {
                maxLocation = temp;
                bestLocation = i + currentLocation;
            }
        }
        return bestLocation;
    }

    /**
     * 根据最佳位置与当前位置计算调动的步伐数量
     * 
     * @param target
     * @param currentLocation
     * @return
     */
    private static int getJumpSteps(int[] target, int currentLocation) {
        int bestLocation = getBestLocation(target, currentLocation);
        // 如果最佳位置与当前位置相等，则说明可以直接从当前位置跳到末端，因此直接返回剩下的步伐
        if (bestLocation == currentLocation) {
            return target.length - currentLocation;
        }
        // 如果不等，则返回最佳位置与当前位置的步伐差
        return bestLocation - currentLocation;
    }

    /**
     * 获取一个正整数数组的最优跳动方式，要求如下:
     * 从数组中间的任意位置开始向右跳，每次跳动的步伐数不能超过该位置对应元素的值
     * 在跳动次数最少的情况下计算每次跳动的步伐
     * 
     * @param target
     * @param currentLocation
     * @return
     * @throws Exception
     * 
     */
    public static String getStepsResult(int[] target, int currentLocation) throws Exception {
        if (null == target || target.length == 0)
            throw new CheckParamException("数组参数为空或未初始化,请检查······");
        for (int i : target)
            if (i < 1)
                throw new CheckParamException("数组成员包含小于非正整数");
        if (currentLocation < 1 || currentLocation > target.length)
            throw new CheckParamException("开始位置参数不正确，必须大于等于1并且小于等于" + target.length);
        String result = "";
        int jumpSteps = getJumpSteps(target, currentLocation);
        while (jumpSteps + currentLocation != target.length) {
            result += jumpSteps + ",";
            currentLocation = getBestLocation(target, currentLocation);
            jumpSteps = getJumpSteps(target, currentLocation);
        }
        return result + jumpSteps;
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {
        int a[] = { 4, 1, 5, 1, 3, 2, 1, 3, 1, 2, 3, 1, 1 };
        System.out.println(getStepsResult(a, 1));
    }

    /**
     * 自定义参数检查异常
     * 
     * @author ZOUHENG
     */
    private static class CheckParamException extends Exception {
        private static final long serialVersionUID = -5470930382435803070L;

        public CheckParamException(String message) {
            super(message);
        }
    }
}
