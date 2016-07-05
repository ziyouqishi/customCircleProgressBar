# customCircleProgressBar
自定义的进度条，可以用来展示分数或者进度。
实线进度条
![实线](https://github.com/ziyouqishi/customCircleProgressBar/blob/master/screenshot/2016-07-05-08mzhaha.gif)
虚线进度条
![虚线](https://github.com/ziyouqishi/customCircleProgressBar/blob/master/screenshot/2016-07-05-08mztest.gif)

自定义属性：

```
<resources>
    <attr name="firstColor" format="color" />
    <attr name="secondColor" format="color" />
    <attr name="thirdColor" format="color" />
    <attr name="textColor" format="color" />
    <attr name="circleWidth" format="dimension" />
    <attr name="speed" format="integer" />
    <attr name="radius" format="dimension" />
    <attr name="grade" format="integer" />
    <attr name="icons" format="reference|color" />
    <attr name="textSizes" format="dimension" />

    <declare-styleable name="CustomProgressBar">
        <attr name="firstColor" />
        <attr name="secondColor" />
        <attr name="thirdColor" />
        <attr name="circleWidth" />
        <attr name="speed" />
        <attr name="radius" />
        <attr name="grade" />
        <attr name="icons" />
        <attr name="textSizes" />
        <attr name="textColor" />
    </declare-styleable>
</resources>
```

布局文件示例：
```
   <com.zhimei.customcircleprogressbar.widget.MyCircleProgressBar
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       custom:circleWidth="10dp"
       custom:speed="20"
       custom:firstColor="@color/colorAccent"
       custom:icons="@mipmap/ic_launcher"
       custom:grade="46"
       custom:textSizes="100sp"
       custom:radius="150dp"
       custom:textColor="@color/colorPrimaryDark"
       />
```
