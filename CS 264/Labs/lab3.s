		.data
userinput:	.space 256
inputsani:	.space 256
array:		.space 212
prompt:		.asciiz "Please input a string: "
prompt2:	.asciiz "\nFrequency of "
colon:		.asciiz ": "
spaces:		.asciiz "spaces: "
newline:	.asciiz "\n"
notpal:		.asciiz "\nInput is not a palindrome!"
pal:		.asciiz "\nInput is a palindrome!"
		.text
		.globl main
						#begin user input
main:		li $v0, 4			#syscall 4 - print string
		la $a0, prompt	
		syscall
		li $v0, 8			#syscall 8 - read string
		la $a0, userinput
		li $a1, 128
		syscall
						#end user input
		jal freqread
						#begin frequency print
		jal freqprint
						#end frequency print			
		jal palendrome
						#begin palendrome print
		beqz $v1, palfalse		#if $v1 = false, palendrome
		b paltrue			#else, is palendrome
						#end palendrome print
freqread:		la $t2, array
			#ascii values:
			#A: 65
			#Z: 90
			#a: 97
			#z: 122
			#' ': 32
freqloop: 
		lb $t3, 0($a0)			#load byte at address'
		addi $a0, $a0, 1
		beqz $t3, freqcont
		beq $t3, 32, spc
		bge $t3, 97, low
		bge $t3, 65, upp
		b freqloop
spc:	li $t1, 208
		b increment
low:	bge $t3, 122, freqloop
		sub $t1, $t3, 97
		addi $t1, $t1, 26
		mul $t1, $t1, 4
		b increment
upp:	bge $t3, 90, freqloop
		sub $t1, $t3, 65
		mul $t1, $t1, 4
increment: add $t1, $t1, $t2
		lw $t0, 0($t1)
		addi $t0, $t0, 1
		sw $t0, 0($t1)
		b freqloop
freqprint: 	li $t0, 0	
			la $t1, array
ploop:		bge $t0, 26, plow
			add $t2, $t0, 65
			li $v0, 11	#print char value
			move $a0, $t2
			syscall
			la $a0, colon
			li $v0, 4
			syscall
			lw $a0, 0($t1) #print frequency
			li $v0, 1
			syscall
			la $a0, newline
			li $v0, 4
			syscall
			addi $t0, $t0, 1
			addi $t1, $t1, 4
			b ploop
plow:	li $t0, 0
plowl:	bge $t0, 26, pspace
		add $t2, $t0, 97
		li $v0, 11
		move $a0, $t2
		syscall
		la $a0, colon
		li $v0, 4
		syscall
		lw $a0, 0($t1)
		li $v0, 1
		syscall
		la $a0, newline
		li $v0, 4
		syscall
		addi $t0, $t0, 1
		addi $t1, $t1, 4
		b plowl
pspace:	la $a0, spaces
		li $v0, 4
		syscall
		lw $a0, 0($t1)
		li $v0, 1
		syscall
		la $a0, newline
		li $v0, 4
		syscall
freqcont: 	jr $ra
palendrome:					#palendromes ignore punctuation and letter case, so we must sanitize string
		li $v1, 0			#set return to false initially
		li $t1, 0			#string length
		li $t3, 0			#character
		la $t0, userinput		#load string
		move $a0, $t0			#i
		la $a1, inputsani
sanitize:	lb $t3, ($t0)
		beqz $t3, isPal
		addi $t0, $t0, 1		#i+1
						#character cases
		bgt $t3, 'z', ignore		#if > z
		blt $t3, 'A', ignore		#if < A
		ble $t3, 'Z', toLowerCase	#if <= Z
		blt $t3, 'a', ignore		#if < a
saveCharacter:	sb $t3, ($a1)			#save char in sanitized string
		addi $t1, $t1, 1		#increment length
		addi $a1, $a1, 1		#increment 
ignore:		b sanitize
toLowerCase:	addi $t3, $t3, 32		#add 32 makes it lowercase
		b saveCharacter
isPal:		li $v0, 0
		la $t0, inputsani		#start
		la $t2, inputsani		#end
		add $t2, $t2, $t1
		addi $t2, $t2, -1
		addi $t1, $t1, 1		
		div $t1, $t1, 2			#middle index
loop:		lb $t3, ($t0)			#front character
		lb $t4, ($t2)			#back character
		addi $t2, $t2, -1		
		addi $t0, $t0, 1		#increment front character
		addi $t1, $t1, -1		#decrement back character
		bne $t3, $t4, return
		bgtz $t1, loop
		li $v1, 1			#if here, loop is done, is palendrome
		b return
palfalse:	li $v0, 4			#syscall 4 - print string
		la $a0, notpal
		syscall	
		b end
paltrue:	li $v0, 4			#syscall 4 - print string
		la $a0, pal
		syscall	
		b end
return:		jr $ra
end:		li $v0, 10			#syscall 10 - end program
		syscall	
