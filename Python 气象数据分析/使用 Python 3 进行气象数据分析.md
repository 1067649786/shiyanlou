
# 使用 Python 3 进行气象数据分析

---

本实验将对意大利北部沿海地区的气象数据进行分析与可视化。我们在实验过程中先会运用 Python 中 matplotlib 库的对数据进行图表化处理，然后调用 scikit-learn 库当中的的 SVM 库对数据进行回归分析，最终在图表分析的支持下得出我们的结论。

### 课程来源

本课程基于 [图灵教育](http://www.ituring.com.cn/) 的 [《Python 数据分析实战》](http://item.jd.com/12027532.html) 第 2 章制作，感谢 [图灵教育](http://www.ituring.com.cn/) 授权实验楼发布。如需系统的学习本书，请购买[《Python 数据分析实战》](http://item.jd.com/12027532.html)。

为了保证可以在实验楼环境中完成本次实验，我们在原书内容基础上补充了一系列的实验指导，比如实验截图，代码注释，帮助您更好得实战。

如果您对于实验有疑惑或者建议可以随时在讨论区中提问，与同学们一起探讨。

### 实验知识点

*   matplotlib 库画出图像
*   scikit-learn 库对数据进行回归分析
*   numpy 库对数据进行切片

---

### 实验原理

气象数据是在网上很容易找到的一类数据。很多网站都提供以往的气压、气温、湿度和降雨量等气象数据。只需指定位置和日期，就能获取一个气象数据文件。这些测量数据是由气象站收集的。气象数据这类数据源涵盖的信息范围较广。数据分析的目的是把原始数据转化为信息，再把信息转化为知识，因此拿气象数据作为数据分析的对象来讲解数据分析全过程再合适不过。

### 待检验的假设：靠海对气候的影响

写作本章时，虽正值夏初，却已酷热难耐，住在大城市的人感受更为强烈。于是周末很多人到山村或海滨城市去游玩，放松一下身心，远离内陆城市的闷热天气。我常常想，靠海对气候有什么影响？这个问题可以作为数据分析的一个不错的出发点。我不想把本章写成科学类读物，只是想借助这样一种方式，让数据分析爱好者能够把所学用于实践，解决 “海洋对一个地区的气候有何影响” 这个问题。

**研究系统：亚得里亚海和波河流域**

既然已定义好问题，就需要寻找适合研究数据的系统，提供适合回答这个问题的环境。首先，需要找到一片海域供你研究。我住在意大利，可选择的海有很多，因为意大利是一个被海洋包围的半岛国家。为什么要把自己的选择局限在意大利呢？因为我们所研究的问题刚好和意大利人的一种典行为相关，也就是夏天我们喜欢躲在海边，以躲避内陆的酷热。我不知道在其他国家这种行为是否也很普遍，因此我只把自己熟悉的意大利作为一个系统进行研究。但是你可能会考虑研究意大利的哪个地区呢？上面说过，意大利是半岛国家，找到可研究的海域不是问题，但是如何衡量海洋对其远近不同的地方的影响？这就引出了一个大问题。意大利其实多山地，离海差不多远，可以彼此作为参照的内陆区域较少。为了衡量海洋对气候的影响，我排除了山地，因为山地也许会引入其他很多因素，比如海拔。

意大利波河流域这块区域就很适合研究海洋对气候的影响。这一片平原东起亚得里亚海，向内陆延伸数百公里（见图 9-1）。它周边虽不乏群山环绕，但由于它很宽广，削弱了群山的影响。此外，该区域城镇密集，也便于选取一组离海远近不同的城市。我们所选的几个城市，两个城市间的最大距离约为 400 公里。

<img width='700px' src="https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489049254603.png">

第一步，选 10 个城市作为参照组。选择城市时，注意它们要能代表整个平原地区（见图 9-2）。

<img width='700px' src="https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489049264587.png">

如图 9-2 所示，我们选取了 10 个城市。随后将分析它们的天气数据，其中 5 个城市在距海 100 公里范围内，其余 5 个距海 100～400 公里。

选作样本的城市列表如下：

*   Ferrara（费拉拉）
*   Torino（都灵）
*   Mantova（曼托瓦）
*   Milano（米兰）
*   Ravenna（拉文纳）
*   Asti（阿斯蒂）
*   Bologna（博洛尼亚）
*   Piacenza（皮亚琴察）
*   Cesena（切塞纳）
*   Faenza（法恩莎）

现在，我们需要弄清楚这些城市离海有多远。方法有多种。这里使用 [TheTimeNow 网站](http://www.thetimenow.com/distance-calculator.php)提供的服务，它支持多种语言（见图 9-3）。

<img width='700px' src="https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489049599588.png">

有了计算两城市间距离这样的服务，我们就可以计算每个城市与海之间的距离。

你可以选择海滨城市 Comacchio 作为基点，计算其他城市与它之间的距离（见图 9-2）。使用上述服务计算完

所有距离后，得到的结果如表 9-1 所示。

<img width='700px' src="https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489049581099.png">
<img width='700px' src="https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489049593848.png">

## 开发准备

定义好要研究的系统之后，我们就需要创建数据源，以获取研究所需的数据。上网浏览一番，你就会发现很多网站都提供世界各地的气象数据，其中就有 Open Weather Map，它的网址是 [http://openweathermap.org/](http://openweathermap.org/) （见图 9-4）。

<img width='700px' src="https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489114032052.png">

<img width='700px' src="https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489114071348.png">

该网站提供以下功能：在请求的 URL 中指定城市，即可获取该城市的气象数据。我们已经准备好了数据，不需要大家再去调用该网站的 API。
下面，就先下载气象数据。

**☞ 示例代码：**


```python
# 下载气象数据
!wget -nc http://labfile.oss.aliyuncs.com/courses/780/WeatherData.zip

# 安装 unzip 解压缩
!apt-get install unzip

# 解压缩
!unzip -o WeatherData.zip
```

**☞ 动手练习：**


```python

```

这时候，我们通过 tree 命令应该能够再 WeatherData 中间看到 10 个城市的天气数据文件（以 .csv 结尾）


```python
!apt-get install tree

!tree WeatherData/
```


```python

```

导入相关包开始实验。


```python
import numpy as np
import pandas as pd
import datetime
```


```python

```

如果你想用本章的数据，需要加载写作本章时保存的 10 个 CSV 文件。


```python
df_ferrara = pd.read_csv('WeatherData/ferrara_270615.csv')
df_milano = pd.read_csv('WeatherData/milano_270615.csv')
df_mantova = pd.read_csv('WeatherData/mantova_270615.csv')
df_ravenna = pd.read_csv('WeatherData/ravenna_270615.csv')
df_torino = pd.read_csv('WeatherData/torino_270615.csv')
df_asti = pd.read_csv('WeatherData/asti_270615.csv')
df_bologna = pd.read_csv('WeatherData/bologna_270615.csv')
df_piacenza = pd.read_csv('WeatherData/piacenza_270615.csv')
df_cesena = pd.read_csv('WeatherData/cesena_270615.csv')
df_faenza = pd.read_csv('WeatherData/faenza_270615.csv')
```


```python

```

我们把这些数据读入内存，完成了实验准备的部分。

### 实验步骤

从数据可视化入手分析收集到的数据是常见的做法。前面讲过，matplotlib 库提供一系列图表生成工具，能够以可视化形式表示数据。数据可视化在数据分析阶段非常有助于发现研究系统的一些特点。

导入以下必要的库：


```python
%matplotlib inline
import matplotlib.pyplot as plt
import matplotlib.dates as mdates
from dateutil import parser
```


```python

```

### 温度数据分析

举例来说，非常简单的分析方法是先分析一天中气温的变化趋势。我们以城市米兰为例。


```python
# 取出我们要分析的温度和日期数据
y1 = df_milano['temp']
x1 = df_milano['day']

# 把日期数据转换成 datetime 的格式
day_milano = [parser.parse(x) for x in x1]

# 调用 subplot 函数, fig 是图像对象，ax 是坐标轴对象
fig, ax = plt.subplots()

# 调整x轴坐标刻度，使其旋转70度，方便查看
plt.xticks(rotation=70)

# 设定时间的格式
hours = mdates.DateFormatter('%H:%M')

# 设定X轴显示的格式
ax.xaxis.set_major_formatter(hours)

# 画出图像，day_milano是X轴数据，y1是Y轴数据，‘r’代表的是'red' 红色
ax.plot(day_milano ,y1, 'r')
```


```python

```

执行上述代码，将得到如图 9-8 所示的图像。由图可见，气温走势接近正弦曲线，从早上开始气温逐渐升高，最高温出现在下午两点到六点之间，随后气温逐渐下降，在第二天早上六点时达到最低值。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489389892193.png)

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489389953824.png/wm)

我们进行数据分析的目的是尝试解释是否能够评估海洋是怎样影响气温的，以及是否能够影响气温趋势，因此我们同时来看几个不同城市的气温趋势。这是检验分析方向是否正确的唯一方式。因此，我们选择三个离海最近以及三个离海最远的城市。


```python
# 读取温度和日期数据
y1 = df_ravenna['temp']
x1 = df_ravenna['day']
y2 = df_faenza['temp']
x2 = df_faenza['day']
y3 = df_cesena['temp']
x3 = df_cesena['day']
y4 = df_milano['temp']
x4 = df_milano['day']
y5 = df_asti['temp']
x5 = df_asti['day']
y6 = df_torino['temp']
x6 = df_torino['day']

# 把日期从 string 类型转化为标准的 datetime 类型
day_ravenna = [parser.parse(x) for x in x1]
day_faenza = [parser.parse(x) for x in x2]
day_cesena = [parser.parse(x) for x in x3]
day_milano = [parser.parse(x) for x in x4]
day_asti = [parser.parse(x) for x in x5]
day_torino = [parser.parse(x) for x in x6]

# 调用 subplots() 函数，重新定义 fig, ax 变量
fig, ax = plt.subplots()
plt.xticks(rotation=70)

hours = mdates.DateFormatter('%H:%M')
ax.xaxis.set_major_formatter(hours)

#这里需要画出三根线，所以需要三组参数， 'g'代表'green'
ax.plot(day_ravenna,y1,'r',day_faenza,y2,'r',day_cesena,y3,'r')
ax.plot(day_milano,y4,'g',day_asti,y5,'g',day_torino,y6,'g')
```


```python

```

上述代码将生成如图 9-9 所示的图表。离海最近的三个城市的气温曲线使用红色，而离海最远的三个城市的曲线使用绿色。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489389987994.png)

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489390017695.png/wm)

如图 9-9 所示，结果看起来不错。离海最近的三个城市的最高气温比离海最远的三个城市低不少，而最低气温看起来差别较小。

我们可以沿着这个方向做深入研究，收集 10 个城市的最高温和最低温，用线性图表示气温最值点和离海远近之间的关系。


```python
# dist 是一个装城市距离海边距离的列表
dist = [df_ravenna['dist'][0],
    df_cesena['dist'][0],
    df_faenza['dist'][0],
    df_ferrara['dist'][0],
    df_bologna['dist'][0],
    df_mantova['dist'][0],
    df_piacenza['dist'][0],
    df_milano['dist'][0],
    df_asti['dist'][0],
    df_torino['dist'][0]
]

# temp_max 是一个存放每个城市最高温度的列表
temp_max = [df_ravenna['temp'].max(),
    df_cesena['temp'].max(),
    df_faenza['temp'].max(),
    df_ferrara['temp'].max(),
    df_bologna['temp'].max(),
    df_mantova['temp'].max(),
    df_piacenza['temp'].max(),
    df_milano['temp'].max(),
    df_asti['temp'].max(),
    df_torino['temp'].max()
]

# temp_min 是一个存放每个城市最低温度的列表
temp_min = [df_ravenna['temp'].min(),
    df_cesena['temp'].min(),
    df_faenza['temp'].min(),
    df_ferrara['temp'].min(),
    df_bologna['temp'].min(),
    df_mantova['temp'].min(),
    df_piacenza['temp'].min(),
    df_milano['temp'].min(),
    df_asti['temp'].min(),
    df_torino['temp'].min()
]
```


```python

```

先把最高温画出来。


```python
fig, ax = plt.subplots()
ax.plot(dist,temp_max,'ro')
```


```python

```

结果如图 9-10 所示。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489390039496.png)

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489390072595.png/wm)

如图 9-10 所示，现在你可以证实，海洋对气象数据具有一定程度的影响这个假设是正确的（至少这一天如此）。

进一步观察上图，你会发现海洋的影响衰减得很快，离海 60～70 公里开外，气温就已攀升到高位。

用线性回归算法得到两条直线，分别表示两种不同的气温趋势，这样做很有趣。我们可以使用 scikit-learn 库的 SVR 方法。

**！注意：这段代码会跑比较久的时间，请耐心等待**


```python
from sklearn.svm import SVR

# dist1是靠近海的城市集合，dist2是远离海洋的城市集合
dist1 = dist[0:5]
dist2 = dist[5:10]

# 改变列表的结构，dist1现在是5个列表的集合
# 之后我们会看到 nbumpy 中 reshape() 函数也有同样的作用
dist1 = [[x] for x in dist1]
dist2 = [[x] for x in dist2]

# temp_max1 是 dist1 中城市的对应最高温度
temp_max1 = temp_max[0:5]
# temp_max2 是 dist2 中城市的对应最高温度
temp_max2 = temp_max[5:10]

# 我们调用SVR函数，在参数中规定了使用线性的拟合函数
# 并且把 C 设为1000来尽量拟合数据（因为不需要精确预测不用担心过拟合）
svr_lin1 = SVR(kernel='linear', C=1e3)
svr_lin2 = SVR(kernel='linear', C=1e3)

# 加入数据，进行拟合（这一步可能会跑很久，大概10多分钟，休息一下:) ）
svr_lin1.fit(dist1, temp_max1)
svr_lin2.fit(dist2, temp_max2)

# 关于 reshape 函数请看代码后面的详细讨论
xp1 = np.arange(10,100,10).reshape((9,1))
xp2 = np.arange(50,400,50).reshape((7,1))
yp1 = svr_lin1.predict(xp1)
yp2 = svr_lin2.predict(xp2)
```


```python

```

然后绘图


```python
# 限制了 x 轴的取值范围
fig, ax = plt.subplots()
ax.set_xlim(0,400)

# 画出图像
ax.plot(xp1, yp1, c='b', label='Strong sea effect')
ax.plot(xp2, yp2, c='g', label='Light sea effect')
ax.plot(dist,temp_max,'ro')
```


```python

```

这里 `np.arange(10,100,10)` 会返回 [10, 20, 30,..., 90]，如果把列表看成是一个矩阵，那么这个矩阵是 1 _9 的。这里 `reshape((9,1))` 函数就会把该列表变为 9_ 1 的， [[10], [20], ..., [90]]。这么做的原因是因为 `predict()` 函数的只能接受一个 N _1 的列表，返回一个 1_ N 的列表。

上述代码将生成如图 9-11 所示的图像。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489473436314.png)

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489473472136.png)

如上所见，离海 60 公里以内，气温上升速度很快，从 28 度陡升至 31 度，随后增速渐趋缓和（如果还继续增长的话），更长的距离才会有小幅上升。这两种趋势可分别用两条直线来表示，直线的表达式为：$ y = ax + b$

其中 a 为斜率，b 为截距。


```python
print(svr_lin1.coef_)  #斜率
print(svr_lin1.intercept_)  # 截距
print(svr_lin2.coef_)
print(svr_lin2.intercept_)
```


```python

```

你可能会考虑将这两条直线的交点作为受海洋影响和不受海洋影响的区域的分界点，或者至少是海洋影响较弱的分界点。


```python
from scipy.optimize import fsolve

# 定义了第一条拟合直线
def line1(x):
    a1 = svr_lin1.coef_[0][0]
    b1 = svr_lin1.intercept_[0]
    return a1*x + b1

# 定义了第二条拟合直线
def line2(x):
    a2 = svr_lin2.coef_[0][0]
    b2 = svr_lin2.intercept_[0]
    return a2*x + b2

# 定义了找到两条直线的交点的 x 坐标的函数
def findIntersection(fun1,fun2,x0):
    return fsolve(lambda x : fun1(x) - fun2(x),x0)

result = findIntersection(line1,line2,0.0)
print("[x,y] = [ %d , %d ]" % (result,line1(result)))

# x = [0,10,20, ..., 300]
x = np.linspace(0,300,31)
plt.plot(x,line1(x),x,line2(x),result,line1(result),'ro')
```


```python

```

执行上述代码，将得到交点的坐标

$ [x,y] = [ 53, 30 ] $

并得到如图 9-12 所示的图表。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489473483440.png)

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489473509646.png)

因此，你可以说海洋对气温产生影响的平均距离（该天的情况）为 53 公里。

现在，我们可以转而分析最低气温。


```python
# axis 函数规定了 x 轴和 y 轴的取值范围
plt.axis((0,400,15,25))
plt.plot(dist,temp_min,'bo')
```


```python

```

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489473518063.png)

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489473539939.png)

在这个例子中，很明显夜间或早上 6 点左右的最低温与海洋无关。如果没记错的话，小时候老师教给大家的是海洋能够缓和低温，或者说夜间海洋释放白天吸收的热量。但是从我们得到情况来看并非如此。我们刚使用的是意大利夏天的气温数据，而验证该假设在冬天或其他地方是否也成立，将会非常有趣。

### 湿度数据分析

10 个 DataFrame 对象中还包含湿度这个气象数据。因此，你也可以考察当天三个近海城市和三个内陆城市的湿度趋势。


```python
# 读取湿度数据
y1 = df_ravenna['humidity']
x1 = df_ravenna['day']
y2 = df_faenza['humidity']
x2 = df_faenza['day']
y3 = df_cesena['humidity']
x3 = df_cesena['day']
y4 = df_milano['humidity']
x4 = df_milano['day']
y5 = df_asti['humidity']
x5 = df_asti['day']
y6 = df_torino['humidity']
x6 = df_torino['day']

# 重新定义 fig 和 ax 变量
fig, ax = plt.subplots()
plt.xticks(rotation=70)

# 把时间从 string 类型转化为标准的 datetime 类型
day_ravenna = [parser.parse(x) for x in x1]
day_faenza = [parser.parse(x) for x in x2]
day_cesena = [parser.parse(x) for x in x3]
day_milano = [parser.parse(x) for x in x4]
day_asti = [parser.parse(x) for x in x5]
day_torino = [parser.parse(x) for x in x6]

# 规定时间的表示方式
hours = mdates.DateFormatter('%H:%M')
ax.xaxis.set_major_formatter(hours)

#表示在图上
ax.plot(day_ravenna,y1,'r',day_faenza,y2,'r',day_cesena,y3,'r')
ax.plot(day_milano,y4,'g',day_asti,y5,'g',day_torino,y6,'g')
```


```python

```

上述代码将生成如图 9-14 所示的图表。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489644107601.png)
![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489644150463.png)

乍看上去好像近海城市的湿度要大于内陆城市，全天湿度差距在 20% 左右。我们再来看一下湿度的极值和离海远近之间的关系，是否跟我们的第一印象相符。


```python
# 获取最大湿度数据
hum_max = [df_ravenna['humidity'].max(),
df_cesena['humidity'].max(),
df_faenza['humidity'].max(),
df_ferrara['humidity'].max(),
df_bologna['humidity'].max(),
df_mantova['humidity'].max(),
df_piacenza['humidity'].max(),
df_milano['humidity'].max(),
df_asti['humidity'].max(),
df_torino['humidity'].max()
]

plt.plot(dist,hum_max,'bo')
```


```python

```

我们把 10 个城市的最大湿度与离海远近之间的关系做成图表，请见图 9-15。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489474250962.png)
![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489474273470.png)


```python
# 获取最小湿度
hum_min = [
df_ravenna['humidity'].min(),
df_cesena['humidity'].min(),
df_faenza['humidity'].min(),
df_ferrara['humidity'].min(),
df_bologna['humidity'].min(),
df_mantova['humidity'].min(),
df_piacenza['humidity'].min(),
df_milano['humidity'].min(),
df_asti['humidity'].min(),
df_torino['humidity'].min()
]
plt.plot(dist,hum_min,'bo')
```


```python

```

再来把 10 个城市的最小湿度与离海远近之间的关系做成图表，请见图 9-16。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489474286281.png)
![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489474309498.png)

由图 9-15 和图 9-16 可以确定，近海城市无论是最大还是最小湿度都要高于内陆城市。然而,在我看来，我们还不能说湿度和距离之间存在线性关系或者其他能用曲线表示的关系。我们采集的数据点数量（10）太少，不足以描述这类趋势。

### 风向频率玫瑰图

在我们采集的每个城市的气象数据中，下面两个与风有关：

*   风力（风向）
*   风速

分析存放每个城市气象数据的 DataFrame 就会发现，风速不仅跟一天的时间段相关联，还与一个介于 0~360 度的方向有关。

例如，每一条测量数据也包含风吹来的方向（图 9-17）。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489474584250.png)

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489474619208.png)

为了更好地分析这类数据，有必要将其做成可视化形式，但是对于风力数据，将其制作成使用笛卡儿坐标系的线性图不再是最佳选择。

要是把一个 DataFrame 中的数据点做成散点图


```python
plt.plot(df_ravenna['wind_deg'],df_ravenna['wind_speed'],'ro')
```


```python

```

就会得到图 9-18 这样的图表，很显然该图的表现力也有不足。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489474718206.png)

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489474738819.png)

要表示呈 360 度分布的数据点，最好使用另一种可视化方法：极区图。

首先，创建一个直方图，也就是将 360 度分为八个面元，每个面元为 45 度，把所有的数据点分到这八个面元中。


```python
hist, bins = np.histogram(df_ravenna['wind_deg'],8,[0,360])
print(hist)
print(bins)
```


```python

```

histogram() 函数返回结果中的数组 hist 为落在每个面元的数据点数量。

[0 5 11 1 0 1 0 0]

返回结果中的数组 bins 定义了 360 度范围内各面元的边界。

[0\. 45\. 90\. 135\. 180\. 225\. 270\. 315\. 360.]

要想正确定义极区图，离不开这两个数组。我们将创建一个函数来绘制极区图，其中部分代码在第 7 章已讲过。我们把这个函数定义为 showRoseWind()，它有三个参数：values 数组，指的是想为其作图的数据，也就是这里的 hist 数组；第二个参数 city_name 为字符串类型，指定图表标题所用的城市名称；最后一个参数 max_value 为整型，指定最大的蓝色值。

定义这样一个函数很有用，它既能避免多次重复编写相同的代码，还能增强代码的模块化程度，便于你把精力放到与函数内部操作相关的概念上。


```python
def showRoseWind(values,city_name,max_value):
    N = 8

    # theta = [pi*1/4, pi*2/4, pi*3/4, ..., pi*2]
    theta = np.arange(2 * np.pi / 16, 2 * np.pi, 2 * np.pi / 8)
    radii = np.array(values)
    # 绘制极区图的坐标系
    plt.axes([0.025, 0.025, 0.95, 0.95], polar=True)

    # 列表中包含的是每一个扇区的 rgb 值，x越大，对应的color越接近蓝色
    colors = [(1-x/max_value, 1-x/max_value, 0.75) for x in radii]

    # 画出每个扇区
    plt.bar(theta, radii, width=(2*np.pi/N), bottom=0.0, color=colors)

    # 设置极区图的标题
    plt.title(city_name, x=0.2, fontsize=20)
```


```python

```

你需要修改变量 colors 存储的颜色表。这里，扇形的颜色越接近蓝色，值越大。定义好函数之后，调用它即可：


```python
showRoseWind(hist,'Ravenna',max(hist))
```


```python

```

运行上述函数，将得到如图 9-19 所示的极区图。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489475823125.png)
![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489475845278.png)

由图 9-19 可见，整个 360 度的范围被分成八个区域（面元），每个区域弧长为 45 度，此外每个区域还有一列呈放射状排列的刻度值。在每个区域中，用半径长度可以改变的扇形表示一个数值，半径越长，扇形所表示的数值就越大。为了增强图表的可读性，我们使用与扇形半径相对应的颜色表。半径越长，扇形跨度越大，颜色越接近于深蓝色。

从刚得到的极区图可以得知风向在极坐标系中的分布方式。该图表示这一天大部分时间风都吹向西南和正西方向。

定义好 showRoseWind() 函数之后，查看其他城市的风向情况也非常简单。


```python
hist, bin = np.histogram(df_ferrara['wind_deg'],8,[0,360])
print(hist)
showRoseWind(hist,'Ferrara', max(hist))
```


```python

```

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489475905364.png)

### 计算风速均值的分布情况

即使是跟风速相关的其他数据，也可以用极区图来表示。

定义 RoseWind_Speed 函数，计算将 360 度范围划分成的八个面元中每个面元的平均风速。


```python
def RoseWind_Speed(df_city):
    # degs = [45, 90, ..., 360]
    degs = np.arange(45,361,45)
    tmp = []
    for deg in degs:
        # 获取 wind_deg 在指定范围的风速平均值数据
        tmp.append(df_city[(df_city['wind_deg']>(deg-46)) & (df_city['wind_deg']<deg)]
        ['wind_speed'].mean())
    return np.array(tmp)
```


```python

```

这里 `df_city[(df_city['wind_deg']>(deg-46)) & (df_city['wind_deg']<deg)]` 获取的是风向大于 `deg-46` 度和风向小于 `deg` 的数据。

RoseWind_Speed() 函数返回一个包含八个平均风速值的 NumPy 数组。该数组将作为先前定义的 showRoseWind() 函数的第一个参数，这个函数是用来绘制极区图的。


```python
showRoseWind(RoseWind_Speed(df_ravenna),'Ravenna',max(hist))
```


```python

```

图 9-21 所示的风向频率玫瑰图表示风速在 360 度范围内的分布情况。

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489476190298.png)

![image](https://doc.shiyanlou.com/document-uid122063labid2638timestamp1489476207560.png)

### 实验总结

本章主要目的是演示如何从原始数据获取信息。其中有些信息无法给出重要结论，而有些信息能够验证假设，增加我们对系统状态的认识，而找出这种信息也就意味着数据分析取得了成功。

如果学完本课程，对书籍其他内容感兴趣欢迎点击以下链接购买书籍：

*   [立即购买《Python 数据分析实战》](http://item.jd.com/12027532.html)

<div style="color: #999;font-size: 12px;">©️ 本课程内容，由作者授权实验楼发布，未经允许，禁止转载、下载及非法传播。</div>
