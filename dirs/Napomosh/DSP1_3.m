clear all; close all;
f = 1050; % ������� �������
T0 = 1/f;
N = 40;
dt = T0/N;
t = 0:dt:10/f;
y = sin(2*pi*f*t); % �������� ������

subplot(2,3,1);
plot(t,y,'r');
grid on
 
%%%%%%%%%%%%    1.5F �� �����    %%%%%%%%%%%%%%%%%%%
fd = 1.5*f; % ������� �������������
td = 1 / fd; % ������ �������������
T = 0:td:10/f; % ������ ����������� �������
Y = sin(2*pi*f*T); % ������ ��������
grid on

k = 0:1:length(T) - 1;
for i = 1 : length(Y);
    fun = sinc((10/f - k * td) / td);
    u1(i) = sum(Y(i).*fun);
end;

subplot(2,3,2);
plot(T,u1,'b-');
legend('1.5F');
grid on




%%%%%%%%%%%%    1.75F �� �������    %%%%%%%%%%%%%%%%%%%
fd = 1.75*f; % ������� �������������
td = 1/fd; % ������ �������������
T = 0:td:10/f; % ������ ����������� �������
Y = sin(2*pi*f*T); % ������ ��������

k = 0:1:length(T) - 1;
for i = 1 : length(Y);
    fun = sinc((10/f - k * td) / td);
    u2(i) = sum(Y(i).*fun);
end;

subplot(2,3,3);
plot(T,u2,'g-');
legend('1.75F');
grid on




%%%%%%%%%%%%    2F �� ����������   %%%%%%%%%%%%%%%%%%%
fd = 2.1*f; % ������� �������������
td = 1/fd; % ������ �������������
T = 0:td:10/f; % ������ ����������� �������
Y = sin(2*pi*f*T); % ������ ��������

k = 0:1:length(T) - 1;
for i = 1 : length(Y);
    fun = sinc((10/f - k * td) / td);
    u3(i) = sum(Y(i).*fun);
end;

subplot(2,3,4);
plot(T,u3,'m-');
axis( [ 0, 0.01, -1, 1 ] );
legend('2F');
grid on




%%%%%%%%%%%%    3F �� ƨ����   %%%%%%%%%%%%%%%%%%%
fd = 3*f; % ������� �������������
td = 1/fd; % ������ �������������
T = 0:td:10/f; % ������ ����������� �������
Y = sin(2*pi*f*T); % ������ ��������



k = 0:1:length(T) - 1;
for i = 1 : length(Y);
    fun = sinc((10/f - k * td) / td);
    u4(i) = sum(Y(i).*fun);
end;

subplot(2,3,5);
plot(T,u4,'y-');
legend('3F');
grid on


%%%%%%%%%%%%    1000F �� ר����   %%%%%%%%%%%%%%%%%%%
fd = 1000*f; % ������� �������������
td = 1/fd; % ������ �������������
T = 0:td:10/f; % ������ ����������� �������
Y = sin(2*pi*f*T); % ������ ��������


k = 0:1:length(T) - 1;
for i = 1 : length(Y);
    fun = sinc((10/f - k * td) / td);
    u5(i) = sum(Y(i).*fun);
end;

subplot(2,3,6);
plot(T,u5,'k-');
legend('1000F');
grid on



