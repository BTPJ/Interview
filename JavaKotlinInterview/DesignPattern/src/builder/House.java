package builder;

/**
 * 房子
 *
 * @author BTPJ  2022/1/13
 */
public class House {
    // 门
    private Object door;
    // 窗户
    private Object window;
    // 地基
    private Object foundation;
    // 框架
    private Object frame;
    // 卫生间
    private Object toilet;

    public static class Builder {
        private final House mHouse;

        public Builder() {
            mHouse = new House();
        }

        public Builder setDoor(Object door) {
            mHouse.door = door;
            return this;
        }

        public Builder setWindow(Object window) {
            mHouse.window = window;
            return this;
        }

        public Builder setFoundation(Object foundation) {
            mHouse.foundation = foundation;
            return this;
        }

        public Builder setFrame(Object frame) {
            mHouse.frame = frame;
            return this;
        }

        public Builder setToilet(Object toilet) {
            mHouse.toilet = toilet;
            return this;
        }

        /**
         * 构建器，核心就是对House对象的一些属性的判断，处理等
         *
         * @return House对象
         */
        public House build() {
            if (mHouse.foundation == null) {
                mHouse.foundation = new Object();
            }
            if (mHouse.frame == null) {
                mHouse.frame = new Object();
            }
            if (mHouse.door == null && mHouse.window != null) {
                mHouse.door = new Object();
            }
            if (mHouse.door != null && mHouse.window == null) {
                mHouse.window = new Object();
            }
            return mHouse;
        }
    }
}
