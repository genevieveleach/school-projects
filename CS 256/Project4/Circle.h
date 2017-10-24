#include "Shape.h"
#include <cmath>

class Circle: public Shape
{
	public:
		Circle(double r) {
			radius = r;
		}
		double calcArea()
		{
		      return M_PI * radius * radius; //area = pi*r^2
		}
		double calcPerimeter()
		{
			return 2 * M_PI * radius; //perimeter = 2*pi*r
		}
	private:
		double radius;
};