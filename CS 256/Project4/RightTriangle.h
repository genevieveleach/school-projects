#include "Shape.h"
#include <cmath>

class RightTriangle: public Shape
{
	public:
		RightTriangle(double h, double b) {
			height = h;
			base = b;
		}
		double calcArea()
		{
		      return base * height / 2; //area = base*height/2

		}
		double calcPerimeter()
		{
			return base + height + sqrt(pow(base, 2) + pow(height, 2)); //perimeter = base*height + hypotenuse
		}
	private:
		double height;
		double base;
};