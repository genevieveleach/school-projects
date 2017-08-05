#include "Rectangle.h"
#include "RightTriangle.h"
#include "Circle.h"
#include <iostream>

using namespace std;

int main()
{
	//will make several (2) instances of each shape
	for(int i = 1; i <= 2; i++) {
		double w;
		double l;
		cout << "Please enter width for rectangle " << i << ": ";
		cin >> w;
		cout << "Please enter length for rectangle " << i << ": ";
		cin >> l;
		Rectangle r(w, l);
		cout << "Area of rectangle " << i << ": " << r.getArea() << endl;
		cout << "Perimeter of rectangle " << i << ": " << r.getPerimeter() << endl;
		cout << endl;
	}
	for(int i = 1; i <= 2; i++) {
		double h;
		double b;
		cout << "Please enter height for right triangle " << i << ": ";
		cin >> h;
		cout << "Please enter base length for right triangle " << i << ": ";
		cin >> b;
		RightTriangle t(h, b);
		cout << "Area of right triangle " << i << ": " << t.getArea() << endl;
		cout << "Perimeter of right triangle " << i << ": " << t.getPerimeter() << endl;
		cout << endl;
	}
	for(int i = 1; i <= 2; i++) {
		double r;
		cout << "Please enter radius for circle " << i << ": ";
		cin >> r;
		Circle c(r);
		cout << "Area of circle " << i << ": " << c.getArea() << endl;
		cout << "Perimeter of circle " << i << ": " << c.getPerimeter() << endl;
		cout << endl;
	}
	return 0;
}