
.data

Init:	.asciiz "Initial array is:\n"
End:	.asciiz "Insertion sort is finished!\n"

left:   .asciiz "["
right:  .asciiz "]"
newLine: .asciiz "\n"
space:   .asciiz " "

	
dataName:

Joe:
	.align 5
	.asciiz "Joe"
Jenny:	
	.align 5
	.asciiz "Jenny"
Jill:	
	.align 5	
	.asciiz "Jill"
John:
	.align 5
	.asciiz "John"
Jeff:	
	.align 5
	.asciiz "Jeff"
Joyce:	
	.align 5	
	.asciiz "Joyce"
Jerry:
	.align 5	
	.asciiz "Jerry"
Janice:
	.align 5
	.asciiz "Janice"
Jake:
	.align 5
	.asciiz "Jake"
Jonna:
	.align 5
	.asciiz "Jonna"
Jack:
	.align 5
	.asciiz "Jack"
Jocelyn:
	.align 5
	.asciiz "Jocelyn"
Jessie:
	.align 5
	.asciiz "Jessie"
Jess:
	.align 5
	.asciiz "Jess"
Janet:
	.align 5
	.asciiz "Janet"
Jane:
	.align 5
	.asciiz "Jane"
	
# char * [] data
Array:  .word Joe, Jenny, Jill, John, Jeff, Joyce, Jerry, Janice, Jake, Jonna, Jake, 
	Jocelyn, Jessie, Jess, Janet, Jane

size:   .word 16  # 16 names

sortedArray: .space 64 

dataAddr: .align 2 # char *
	  .space 64 # 16 * 4
	
.text	

.globl main
main:
	la $t0, Array # load address of array to $t0
	la $t1, dataAddr # load address of pointers to $t1
	li $t2, 0     # i = 0
	
#init_loop for coping the address of items in the array to dataAddr
init_loop:	
	beq $t2, 16, end_loop # while i < 16
	sw $t0, ($t1)         # dataAddr[i] = &Array[i]
	addi $t0, $t0, 32     # move array to the next word(2^5 = 32)
	addi $t1, $t1, 4      # move dataAddr to the next pointer 
	addi $t2, $t2, 1
	j init_loop

end_loop:	
	# print the initial message
	li $v0, 4
	la $a0, Init
	syscall
	
	#print the initial array
	la $a0, dataAddr
	lw $a1, size
	jal print_array
	
	# call insertSort
	la $a0, dataAddr
	lw $a1, size
	jal insertSort
	sw $v0, sortedArray
	
	#print the end message
	li $v0, 4
	la $a0, End
	syscall
	
	#print the result array
	lw $a0, sortedArray
	lw $a1, size
	jal print_array
	
	#exit the program
	li $v0, 10
	syscall
	
	
insertSort:
	addi $sp, $sp, -20
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	
	move $s0, $a0 # a0 = array
	move $s1, $a1 # a1 = size
	li $s2, 1 # i = 1 

insert_loop:
	beq $s2, $s1, end_insert
	la $t0, 0($s0)
	li $t1, 4
	mul $t2, $t1, $s2
	add $t3, $t2, $t0 
	lw $s3, ($t3)
	
	
char_loop:
	subi $s4, $s2, 1 # j = i - 1
	
	beq $s4, $zero, end_char # j >= 0
	move $a0, $s3 # a[i] as argument for str_lt
	
	la $t0, ($s0) 
	li $t1, 4
	mul $t2, $t1, $s4 
	add $t3, $t0, $t2
	lw $s3, ($t3)  # load the value of a[j] for use later
	 
	move $a1, $s3 # a[j] as another argument for str_lt
	
	jal str_lt
	move $t0, $v0
	beq $t0, $zero, end_char
		
	addi $s4, $s4, 1 # j + 1
	
	la $t0, ($s0)
	li $t1, 4
	mul $t2, $t1, $s4
	add $t3, $t0, $t2 
	
	lw $s3, ($t3) #a[j+1] = a[j]
	
	subi $s4, $s4, 1 # j--
	j char_loop
	
end_char:
	la $t0, ($s0)
	li $t1, 4
	mul $t2, $t1, $s4 #(j+1) * 4
	add $t3, $t2, $t0 # $t3 = value of a[j+1]
	sw $s3, ($t3) # $s3= value; a[j+1] = value
	
	addi $s2, $s2, 1 # i++
	j insert_loop
	
end_insert:
	lw $s4, 20($sp)
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 20
	
	jr $ra
	

str_lt:
	#prolog
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	move $t0, $a0 # t0 = char* x
	move $t1, $a1 # t1 = char* y
	
	string_loop:
	lb $t2, 0($t0) # t2 = char* x[0]
	lb $t3, 0($t1) # t3 = char* y[0]
	
	and $t4, $t3, $t2
	beq $t4, $zero, end_string
	blt $t2, $t3, one
	bgt $t2, $t3, zero
	
	addi $t0, $t0, 1 # x++
	addi $t1, $t1, 1 # y++
	j string_loop
	
	end_string:
	beq $t3, $zero, zero
	j one
	
	one:
	li $v0, 1
	j end_lt
	
	zero:
	li $v0, 0
	j end_lt
	
	end_lt:
	addi $sp, $sp, 4
	lw $ra, 0($sp)
	
	jr $ra
	
print_array:
    addi $sp, $sp -4
	sw $ra, 0($sp)
	
	move $t0, $a0
	move $t1, $a1 #int i=size
	
	print_loop:
	beq $t1, $zero, end_print #while i > 0

	lw $a0, ($t0) #printf( a[i] )
	li $v0, 4
	syscall
	
	addi $t0, $t0, 4
	addi $t1, $t1, -1
.data
chars:	.asciiz ", "
.text
	beq $t1, 0, end_print
	la $t3, chars
	move $a0, $t3
	li $v0, 4
	syscall
	j print_loop

.text
	end_print:
	la $t0, newLine
	move $a0, $t0
	li $v0, 4
	syscall
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	jr $ra
	
Exit:	li $v0, 10   # load exit operation
	syscall      #exit
