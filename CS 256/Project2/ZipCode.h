#include <iostream>
#include <vector>
using namespace std;
class ZipCode {
	ZipCode(int input);
	ZipCode(string input);
	
	public:
	int getInteger();
	string getBinaryString();
	
	private:
	vector<int> zipcode;
	void convertToInt(vector<int> zipcode);
	void convertToBString(int input);
};
