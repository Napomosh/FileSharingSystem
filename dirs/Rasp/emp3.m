close all
clear command
clc
x = [0
5
10
15
20
25
30
35
40
45
];
y = [1.00
1.00
0.96
0.91
0.71
0.71
0.58
0.58
0.29
0.00
];
y1 = [1.00
0.95
0.84
0.71
0.63
0.55
0.50
0.32
0.32
0.00
];
y2=[1.00
0.93
0.76
0.53
0.41
0.41
0.41
0.24
0.24
0.00
];

plot(x, y, 'r*-');
xlabel('\phi, ����');
hold on
plot(x, y1, 'b*-');
hold on
plot(x, y2, 'g*-');
hold on
grid on
    