	.data
array:	.space 80
prompt1:.asciiz "Please enter an integer: "
prompt2:.asciiz "\nYour integers, one per line:\n"
prompt3:.asciiz "\nYour integers in order: "
prompt4:.asciiz "\nYour integers in reverse: "
prompt5:.asciiz "\nPlease enter a positive integer, between 1 and 20: "
prompt6:.asciiz "\nYour integers, separated by the entered number per line:\n"
newline:.asciiz "\n"
space:	.asciiz " "
	.text
	.globl main
main:	la $t0, array
	li $t1, 0		#number of integers entered
	li $t2, 20		#loop counter
loop:	beq $t1, $t2, print1	#if integers = 20, move to print
	la $a0, prompt1
	li $v0, 4		#syscall 4 - print string
	syscall
	li $v0, 5		#syscall 5 - read integer
	syscall
	sw $v0, 0($t0)		#store in array
	addi $t1, $t1, 1	#increment integer counter
	addi $t0, $t0, 4	#next array position
	b loop			#loop back to beginning
print1:	la $a0, prompt2
	li $v0, 4		#syscall 4 - print string
	syscall
	li $a1, 0 		#initialize a1
	b print1l
print1l:la $t5, array($a1) 	#set to array[i]
	lw $a0, 0($t5) 		#load array[i]
	li $v0, 1		#syscall 1 - print integer
	syscall
	li $v0, 4		#syscall 4 - print string
	la $a0, newline
	syscall
	addi $t2, $t2, -1	#decrement loop counter
	addi $a1, $a1, 4 	#i+1
	bnez $t2, print1l
print2: la $a0, prompt3
	li $v0, 4		#syscall 4 - print string
	syscall
	li $t2, 20		#set loop counter back to 20
	li $a1, 0 		#initialize a1
	b print2l
print2l:la $t5, array($a1) 	#set to array[i]
	lw $a0, 0($t5) 		#load array[i]
	li $v0, 1		#syscall 1 - print integer
	syscall
	li $v0, 4		#syscall 4 - print string
	la $a0, space
	syscall
	addi $t2, $t2, -1	#decrement loop counter
	addi $a1, $a1, 4 	#i+1
	bnez $t2, print2l
	li $v0, 4		#syscall 4 - print string
	la $a0, newline
	syscall
print3: la $a0, prompt4
	li $v0, 4		#syscall 4 - print string
	syscall
	li $t2, 20		#set loop counter back to 20
	addi $a1, $a1, -4 	#set a1 to end of array
	b print3l
print3l:la $t5, array($a1) 	#set to array[i]
	lw $a0, 0($t5) 		#load array[i]
	li $v0, 1		#syscall 1 - print integer
	syscall
	li $v0, 4		#syscall 4 - print string
	la $a0, space
	syscall
	addi $t2, $t2, -1	#decrement loop counter
	addi $a1, $a1, -4 	#i-1
	bnez $t2, print3l
	li $v0, 4		#syscall 4 - print string
	la $a0, newline
	syscall
print4:	la $a0, prompt5
	li $v0, 4		#syscall 4 - print string
	syscall
	li $v0, 5		#syscall 5 - read integer
	syscall
	move $t6, $v0		#store integer for how many per line
	li $t1, 0		#initialize integer per line counter
	li $t2, 20		#set loop counter back to 20
	li $a1, 0 		#initialize a1
	b print4l
print4l:la $t5, array($a1) 	#set to array[i]
	lw $a0, 0($t5) 		#load array[i]
	li $v0, 1		#syscall 1 - print integer
	syscall
	li $v0, 4		#syscall 4 - print string
	la $a0, space
	syscall
	addi $t1, $t1, 1	#increment integer per line counter
	addi $t2, $t2, -1	#decrement loop counter
	addi $a1, $a1, 4 	#i+1
	blez $t2, end		#no more integers; end of program
	beq $t1, $t6, lbreak	#if entered integers are equal to entered number per line, new line
	b print4l
lbreak:	li $v0, 4		#syscall 4 - print string
	la $a0, newline
	syscall
	li $t1, 0		#set number per line back to 0
	b print4l
end:	li $v0, 10 		#syscall 10 - end program
	syscall