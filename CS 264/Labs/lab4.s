			.data
array:		.space 480 
newline:	.asciiz	"\n"
prompt1:	.asciiz "Please enter name of employee: "
prompt2:	.asciiz "Please enter age of employee: "
prompt3:	.asciiz "Please enter salary of employee to the nearest dollar: "	#nearest dollar because it's an int
prompt4:	.asciiz "\nPlease enter a record number to be swapped (0-9): "
prompt5:	.asciiz "Please enter a record number to swap with the previous record (0-9): "
prompt6:	.asciiz "Invalid index. Please re-enter both indices."
name:		.asciiz "\n\nEmployee name: "
age:		.asciiz "Employee age: "
salary:		.asciiz "\nEmployee salary: "
			.text
			.globl main
main:		li $t0, 10			#counter
			la $t1, array			#address of array in $t1
readloop:	la $a0, prompt1		#enter name prompt
			li $v0, 4
			syscall
			move $a0, $t1
			li $a1, 40
			li $v0, 8
			syscall
			la $a0, prompt2		#enter age prompt
			li $v0, 4
			syscall
			li $v0, 5			#read age
			syscall
			sw $v0, 40($t1)		#offset 40 for after string
			la $a0, prompt3		#enter salary prompt
			li $v0, 4
			syscall
			li $v0, 5			#read salary
			syscall
			sw $v0, 44($t1)		#offset 44 for after string and integer
			addi $t0, $t0, -1	#decrement counter
			addi $t1, $t1, 48	#increment to next employee posision
			bnez $t0, readloop
print:		li $t0, 10			#reset counter
			la $t1, array		#hold address of array
printloop:	la $a0, name	
			li $v0, 4
			syscall
			move $a0, $t1		#print name
			li $v0, 4
			syscall
			la $a0, age
			li $v0, 4
			syscall
			lw $a0, 40($t1)		#print age (offset 40 for after string)
			li $v0, 1
			syscall
			la $a0, salary
			li $v0, 4
			syscall
			lw $a0, 44($t1)		#print salary (offset 44 for after string and age)
			li $v0, 1
			syscall
			addi $t0, $t0, -1	#decrement counter
			addi $t1, $t1, 48	#increment to next employee
			bnez $t0, printloop
swap:		la $a0, prompt4		#enter first index
			li $v0, 4
			syscall
			li $v0, 5
			syscall
			blt $v0, 0, outofbounds
			bgt $v0, 9, outofbounds
			move $t0, $v0		#save value of first index
			la $a0, prompt5		#enter second index
			li $v0, 4
			syscall
			li $v0, 5
			syscall
			blt $v0, 0, outofbounds
			bgt $v0, 9, outofbounds
								#value of second index is in $v0
			la $t1, array
			mul $t0, $t0, 48	#multiply to location of employee $t0
			mul $v0, $v0, 48	#multiply to location of employee $v0
			add $t2, $t1, $t0
			add $t3, $t1, $v0
			li $t4, 12
swapLoop:	lw $t5, 0($t2)
			lw $t6, 0($t3)
			sw $t5, 0($t3)
			sw $t6, 0($t2)
			addi $t4, $t4, -1
			addi $t2, $t2, 4
			addi $t3, $t3, 4
			bnez $t4, swapLoop
			li $t0, 10
			la $t1, array
printafterswap:la $a0, name	
			li $v0, 4
			syscall
			move $a0, $t1		#print name
			li $v0, 4
			syscall
			la $a0, age
			li $v0, 4
			syscall
			lw $a0, 40($t1)		#print age (offset 40 for after string)
			li $v0, 1
			syscall
			la $a0, salary
			li $v0, 4
			syscall
			lw $a0, 44($t1)		#print salary (offset 44 for after string and age)
			li $v0, 1
			syscall
			addi $t0, $t0, -1	#decrement counter
			addi $t1, $t1, 48	#increment to next employee
			bnez $t0, printafterswap
			b end	
outofbounds:la $a0, prompt6
			li $v0, 4
			syscall
			b swap	
end: 		li $v0, 10
			syscall


