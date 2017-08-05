	.data
array:	.space 40
prompt1:.asciiz "Please enter a number for the size of array (1-10): "
prompt2:.asciiz "Please enter a number: "
prompt3:.asciiz "To compute combination of n and r, please enter n >= 0: "
prompt4:.asciiz "To compute combination of n and r, please enter r >= 0 and r <= n:"
result1:.asciiz "Smallest integer result: "
result2:.asciiz "Combination result: "
error: .asciiz "Invalid value. Please re-enter both n and r."
newline:.asciiz "\n"
	.text
	.globl main
main: jal fillarr
la $a0, array #load address of A[]
jal min
move $t0, $v0 #move result to t0
la $a0, newline
li $v0, 4
syscall
la $a0, result1
li $v0, 4
syscall
move $a0, $t0 #move result to a0 to be printed
li $v0, 1
syscall
la $a0, newline
li $v0, 4
syscall

jal combuser
move $t0, $v0 #move result to t0
la $a0, newline
li $v0, 4
syscall
la $a0, result2
li $v0, 4
syscall
move $a0, $t0 #move result to a0 to be printed
li $v0, 1
syscall
la $a0, newline
li $v0, 4
syscall

end: li $v0, 10
syscall

fillarr: la $a0, prompt1
li $v0, 4
syscall
li $v0, 5
syscall
move $t1, $v0 #set loop counter to user input
move $a2, $v0 #set high for min function
addi $a2, $a2, -1 #-1 for array indices starting at 0
li $a1, 0 #set low for min function later
la $t0, array
b fillloop

fillloop: la $a0, prompt2
li $v0, 4		
syscall
li $v0, 5
syscall
sw $v0, 0($t0) #store in array
addi $t1, $t1, -1 #decrement loop counter
addi $t0, $t0, 4 #next array position
bnez $t1, fillloop #if loop counter != 0 loop back
jr $ra #else return to main

min:bne $a1, $a2, mincont
sll $a1, $a1, 2
add $t0, $a0, $a1
lw $v0, 0($t0)
jr $ra
mincont:addiu $sp, $sp, -20
sw $ra, 0($sp)
sw $a0, 4($sp)
sw $a2, 8($sp)
add $a2, $a1, $a2
srl $a2, $a2, 1
sw $a2, 12($sp)
jal min
sw $v0, 16($sp)
lw $a0, 4($sp)
lw $a2, 8($sp)
lw $a1, 12($sp)
addi $a1, $a1, 1
jal min
lw $t0, 16($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 20
bgt $t0, $v0, mincont2
move $v0, $t0
mincont2: jr $ra

combuser: la $a0, newline
li $v0, 4
syscall
la $a0, prompt3		
syscall
li $v0, 5
syscall
bltz $v0, err
move $t0, $v0 #store n in $t0
li $v0, 4
la $a0, prompt4		
syscall
li $v0, 5
syscall
bltz $v0, err
blt $t0, $v0, err
move $a1, $v0 #store r in $a1
move $a0, $t0 #store n in $a0
b comb

err: la $a0, newline
li $v0, 4
syscall
la $a0, error
li $v0, 4		
syscall
la $a0, newline
syscall
b combuser

comb:beq $a0, $a1, combcont
beqz $a1, combcont
addiu $sp, $sp, -16
sw $ra, 0($sp)
addi $a0, $a0, -1
sw $a0, 4($sp)
sw $a1, 8($sp)
jal comb
sw $v0, 12($sp)
lw $a0, 4($sp)
lw $a1, 8($sp)
addi $a1, $a1, -1
jal comb
lw $t0, 12($sp)
add $v0, $t0, $v0
lw $ra, 0($sp)
addiu $sp, $sp, 16
jr $ra
combcont: li $v0, 1
jr $ra
