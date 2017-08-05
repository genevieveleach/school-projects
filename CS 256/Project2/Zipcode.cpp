//Genevieve Leach
//Jarod Nakamoto
//CS 256
//Assignment 2

/*
Current issues:
Due to the assignment asking to only store in either binary string or integer value, zip code is always converted to integer. Due to the last digit of the zipcode multiplier being 0, it will always return 0 as the fifth digit of the zip code, even when a different number is provided in the original binary string input.
*/

#include <iostream>
#include <vector>
#include <string>
#include <sstream>

using namespace std;

class ZipCode {
	public:
	ZipCode(int input);
	ZipCode(string input);
	int getInteger();
	string getBinaryString();
	
	private:
	int zipcode;
	static const int multiplier[];
	int convertToInt(string input);
	void convertToVector(string input);
	string convertToBString(int input);
};

const int ZipCode::multiplier[] = {7, 4, 2, 1, 0};

ZipCode::ZipCode(int input) {
	zipcode = input;
}

ZipCode::ZipCode(string input) {
	zipcode = convertToInt(input);
}

int ZipCode::getInteger() {
	return zipcode;
}

string ZipCode::getBinaryString() {
	string output = convertToBString(zipcode);
	return output;
}

int ZipCode::convertToInt(string input) {
	int ans = 0;
	int counter = 0;
	int digit = 0;
	for(int i = 0; i < input.size(); i++) {
		if(input.at(i)!=' ') {
			int temp = input.at(i);
			digit += ((int)(temp-48))*multiplier[counter];
			counter++;
			if(counter > 4){
				counter = 0;
				if(digit > 9){
					digit = 0;
				}
				ans = ans*10;
				ans += digit;
			}
		}
		else {
			counter = 0;
			digit = 0;
		}
	}
	return ans;
}

string ZipCode::convertToBString (int input) {
	int temp = input;
	string str = "";
	for(int i = 1; i <= 5; i++) {
		string tempstr = "";
		int digit = temp%10;
		temp = temp/10;
		for(int j = 0; j < 4; j++) {
			tempstr += (char)(digit/multiplier[j]+48);
			digit = digit%multiplier[j];
		}
		tempstr += "0";
		str.insert(0, tempstr);
		if (i != 5){
			str.insert(0, "  ");
		}
	}
	return str;
}

int main() {
	string choice;
	int iInput;
	string sInput;
	string sOutput;
	int iOutput;
	
	cout << "Would you like to encode or decode a zip code?\nPlease type \"encode\" or \"decode\": ";
	cin >> choice;
	
	if(!choice.compare("encode")) {
		cout << "Please input a zip code in integer format: ";
		cin >> iInput;
		ZipCode zip(iInput);
		
		cout << "Would you like the entered zip code returned as a binary string or an integer?\nPlease type \"string\" or \"integer\": ";
		cin >> choice;
		cout << endl;
		
		if(!choice.compare("string")) {
			sOutput = zip.getBinaryString();
			cout << "The zip code is: " << sOutput << endl;
		} else {
			iOutput = zip.getInteger();
			cout << "The zip code is: " << iOutput << endl;
		}
	} else {
		cin.ignore();
		cout << "Please input a zip code in binary format: ";
		getline(cin,sInput);
		cout<< sInput;
		ZipCode zip(sInput);
		
		cout << "Would you like the entered zip code returned as a binary string or an integer?\n Please type \"string\" or \"integer\": ";
		cin >> choice;
		cout << endl;
		
		if(!choice.compare("string")) {
			sOutput = zip.getBinaryString();
			cout << "The zip code is: " << sOutput << endl;
		} else {
			iOutput = zip.getInteger();
			cout << "The zip code is: " << iOutput << endl;
		}
	}
	return 0;
}