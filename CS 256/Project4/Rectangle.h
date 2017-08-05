#include "Shape.h"

class Rectangle: public Shape
{
	public:
		Rectangle(double w, double l) {
			width = w;
			length = l;
		}
		double calcArea()
		{
		      return width * length; //area = length*width
		}
		double calcPerimeter()
		{
			return 2*width + 2*length; //perimeter = 2*width + 2*length
		}
	private:
		double width;
		double length;
};
