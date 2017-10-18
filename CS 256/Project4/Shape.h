#ifndef SHAPE_H //makes sure shape is not redefined in any derivative classes
#define SHAPE_H

class Shape
{
	public:
		virtual double calcArea() = 0; //virtual so it can be correctly used in derivative classes
		virtual double calcPerimeter() = 0;
		double getArea() //calls calcArea for each shape, then returns area
		{
			area = calcArea(); 
			return area;
		}
		double getPerimeter() //calls calcPerimeter for each shape then returns perimeter
		{
			perimeter = calcPerimeter();
			return perimeter;
		}

	private:
		double area;
		double perimeter;
};

#endif