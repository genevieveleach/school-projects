// Genevieve Leach
// Jarod Nakamoto
// CS 256, Section 1
// Assignment 3

/*
Current Issues:
Does not average correctly with 3+ items across 3+ menus
*/

#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>

using namespace std;

void openFile(string fileName);
void addToMap(string line);
void writeFile();
unordered_map<string,double> menu;

int main(int argc, char* argv[]) {
	//argv[i>=1] holds the file names
	if(argc == 1) { //no menus, just ./a.out for arguments
		cout << "No input files provided! Nothing has been written to output file." << endl;
		return 0;
	}
	for(int i = 1; i < argc; i++) {
		openFile(argv[i]);
	}
	cout << "All files finished." << endl;
	writeFile();
	cout << "Menu written to \"output.txt\"" << endl;
	return 0;
}

void openFile(string fileName) {
	ifstream inFile;
	inFile.open(fileName);
	if (!inFile.good()) {
		cout << "File \"" << fileName << "\" not found in the directory. Continuing to next file." << endl;
		return;
	} else {
		cout << "File \"" << fileName << "\" was found." << endl;
	}
	
	while (!inFile.eof()) {
		for(string line; getline(inFile, line); ) { //goes through each line until the end of file character
			addToMap(line);
		}
	}
	inFile.close();
}

void addToMap(string line) {
	string word;
	double price;
	int index = line.rfind(' '); //finds last space before price
	word = line.substr(0, index);
	price = stod(line.substr(index));
	try {
		menu.at(word);
	} catch (const out_of_range& oor) {
		//if here, it doesn't have that food item
		menu[word] = price;
		return;
	} //else, need to average the prices
	double temp = menu[word];
	menu[word] = (temp+price)/2; //INCORRECT WAY OF AVERAGING MORE THAN 2 MENUS
}

void writeFile() {
	FILE * outFile;
	outFile = fopen ("output.txt", "w"); // opens output to write
	unordered_map<string, double>::iterator it = menu.begin();
	while (it != menu.end()) { //iterates through the map, printing every key->value pair
		fprintf(outFile, "%s %.2f\n", it->first.c_str(), it->second); //in format Item Price.xx
		it++;
	}
}
