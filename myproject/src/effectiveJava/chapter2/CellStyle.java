package effectiveJava.chapter2;

import java.util.EnumSet;
import java.util.Set;

/**
 * builder模式
 * itext pdf单元格格式实体
 * 使用示例：
 * CellStyle cellStyle = new Builder(11).setSide(Side.UP_SIDE)
 * .setBlod(Blod.NO_BLOD).setHeight(12).setFontPath("C:/WINDOWS/Fonts/SIMYOU.TTF").build();
 * PdfPTable table = new PdfPTable(1);
 * PDFGenerateUtil.checkExist("haha", cellStyle, table);
 */
public class CellStyle {
    //字体高度
    private float height;
    //字体加粗
    private int blod;
    //字体路径
    private String fontPath;
    //单元格合并行数
    private int mergeRowLines;
    //单元格合并列数
    private int mergeColumnLines;
    //字体大小
    private int size;
    //水平对齐方式
    private int level;
    //垂直对齐方式
    private int vertical;
    //旋转方式
    private int rotate;
    /**
     * 隐藏边框
     * 0 表示不隐藏 1代表上边框 2代表下边框 4代表左边框 8代表右边框
     * 需要隐藏那些边框就把对应的值加起来，得到的和就是要设置的值
     * 比如要隐藏左右边框 就是 4+8=12
     */
    private int side;

    /**
     * 边框设置枚举  0 表示不隐藏 1代表上边框 2代表下边框 4代表左边框 8代表右边框
     * 需要隐藏那些边框就把对应的值加起来，得到的和就是要设置的值
     * 比如要隐藏左右边框 就是 4+8=12比如要隐藏左右边框 就是 4+8=12
     * 这个地方其实是位域表示  可以优化
     */
    public enum Side {
        /**
         * 利用位域改造枚举
         */
        NO_SIDE(0), UP_SIDE(1), DOWN_SIDE(1 << 1), LEFT_SIDE(1 << 2), RIGHT_SIDE(1 << 3);
        private int side;

        Side(int side) {
            this.side = side;
        }

        public int getSide() {
            return side;
        }

        @Override
        public String toString() {
            return "Side{" +
                    "side=" + side +
                    '}';
        }
    }

    public enum Blod {
        NO_BLOD(0), IS_BLOD(1);
        private int blod;

        Blod(int blod) {
            this.blod = blod;
        }

        public int getBlod() {
            return blod;
        }

        public void setBlod(int blod) {
            this.blod = blod;
        }
    }

    /**
     * Builder
     */
    public static class Builder {
        private int height = 0;//自适应高度
        private int size;//唯一指定要传参数 字体大小
        private int mergeRowLines = 1;//默认为1表示不合并
        private int mergeColumnLines = 1;//默认为1表示不合并
        private int level = 1;//默认水平居中
        private int vertical = 5;//默认垂直居中
        private int rotate = 0;//为0表示不旋转
        private int side = 0;//表示不隐藏
        private int blod = 0;//默认不加粗
        private String fontPath;//字体路径 若不传路径则使用itext自带字体

        public Builder(int size) {
            this.size = size;
        }

        /**
         * 高度设置
         *
         * @param height
         * @return
         */
        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        /**
         * 水平对齐方式设置
         *
         * @param level
         * @return
         */
        public Builder setHorizontalWay(int level) {
            this.level = level;
            return this;
        }

        /**
         * 垂直对齐方式设置
         *
         * @param vertical
         * @return
         */
        public Builder setVerticalWay(int vertical) {
            this.vertical = vertical;
            return this;
        }

        /**
         * 旋转方式
         *
         * @param rotate
         * @return
         */
        public Builder setRotate(int rotate) {
            this.rotate = rotate;
            return this;
        }

        /**
         * 设置边框隐藏方式
         *
         * @param enumSet
         * @return
         */
        public Builder setSide(Set<Side> enumSet) {
            int value = 0;
            for (Side item : enumSet) {
                value += item.getSide();
            }
            this.side = value;
            return this;
        }

        /**
         * 是否加粗设置
         *
         * @param blod
         * @return
         */
        public Builder setBlod(Blod blod) {
            this.blod = blod.getBlod();
            return this;
        }

        /**
         * 字体路径设置
         *
         * @param fontPath
         * @return
         */
        public Builder setFontPath(String fontPath) {
            this.fontPath = fontPath;
            return this;
        }

        /**
         * 设置行合并数
         *
         * @param rowLines
         * @return
         */
        public Builder setMergeRowLines(int rowLines) {
            this.mergeRowLines = rowLines;
            return this;
        }

        /**
         * 设置列合并数
         *
         * @param columnLines
         * @return
         */
        public Builder setMergeColumnLines(int columnLines) {
            this.mergeColumnLines = columnLines;
            return this;
        }

        public CellStyle build() {
            return new CellStyle(this);
        }
    }

    public CellStyle(Builder builder) {
        this.height = builder.height;
        this.blod = builder.blod;
        this.size = builder.size;
        this.mergeRowLines = builder.mergeRowLines;
        this.mergeColumnLines = builder.mergeColumnLines;
        this.level = builder.level;
        this.vertical = builder.vertical;
        this.rotate = builder.rotate;
        this.side = builder.side;
        this.fontPath = builder.fontPath;
    }

    public float getHeight() {
        return height;
    }

    public int getBlod() {
        return blod;
    }

    public String getFontPath() {
        return fontPath;
    }

    public int getMergeRowLines() {
        return mergeRowLines;
    }

    public int getMergeColumnLines() {
        return mergeColumnLines;
    }

    public int getSize() {
        return size;
    }

    public int getLevel() {
        return level;
    }

    public int getVertical() {
        return vertical;
    }

    public int getRotate() {
        return rotate;
    }

    public int getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "CellStyle{" +
                "height=" + height +
                ", blod=" + blod +
                ", fontPath='" + fontPath + '\'' +
                ", mergeRowLines=" + mergeRowLines +
                ", mergeColumnLines=" + mergeColumnLines +
                ", size=" + size +
                ", level=" + level +
                ", vertical=" + vertical +
                ", rotate=" + rotate +
                ", side=" + side +
                '}';
    }



}
