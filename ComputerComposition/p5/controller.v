`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:47:20 11/30/2016 
// Design Name: 
// Module Name:    controller 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module controller(
	input [5:0]A,
	input [5:0]B,
	
	output [1:0]REGDst,
	output [1:0]ALUSrc,
	output RegWrite,
	output [5:0]ALUoperation,
	output MemWrite,
	output [1:0]MemtoReg,
	output W,
	output Branch,
	output Branch2,
	output Branch3,
	output [2:0]J
    );
	wire r,addu,subu,and1,or1,xor1,sll,srl,sra,srav,addi,
		  ori,lw,lwe,sw,lw2,
		  beq,bne,
		  lui,nop,j,jal,jr,
		  seh,
		  bnzalr,jialc,ji; 
	assign r=(A==6'b000000&&B!=6'b001000)?1:0,
			ori=(A==6'b001101)?1:0,
			addi=(A==6'b001001|A==6'b001000)?1:0,
			lw=(A==6'b100011)?1:0,
			sw=(A==6'b101011)?1:0,
			beq=(A==6'b000100)?1:0,
			bne=(A==6'b000101)?1:0,
			lui=(A==6'b001111)?1:0,
			lwe=(A==6'b011111&&B==6'b101111)?1:0,
			seh=(A==6'b011111&&B==6'b100000)?1:0,
			j=(A==6'b000010)?1:0,
			jal=(A==6'b000011)?1:0,
			lw2=(A==6'b110001)?1:0,
			ji=(A==6'b110110)?1:0,
			jr=(A==6'b000000&&B==6'b001000)?1:0,
			bnzalr=(A==6'b111111)?1:0,
			jialc=(A==6'b111110)?1:0;

	assign addu=(((r==1)&&(B==6'b100001|B==6'b100000))?1:0),
			subu=(((r==1)&&(B==6'b100011|B==6'b100010))?1:0),
			and1=(((r==1)&&(B==6'b100100))?1:0),
			xor1=(((r==1)&&(B==6'b100110))?1:0),
			sll=(((r==1)&&(B==6'b000000))?1:0),
			srl=(((r==1)&&(B==6'b000010))?1:0),
			sra=((r==1)&&(B==6'b000011)?1:0),
			srav=((r==1)&&(B==6'b000111)?1:0),
			or1=(((r==1)&&(B==6'b100101))?1:0);
		
	assign REGDst=(jal||jialc?2'b10:(r||lw2||bnzalr||seh?2'b01:2'b00)),
			ALUSrc=(lw||lwe||sw||ori||addi||lui?2'b10:(bnzalr?2'b11:2'b00)),
			RegWrite=bnzalr||seh||ori||lw||lw2||lwe
						||lui||addu||subu||and1||or1||xor1
						||sll||srl||sra||srav
						||jialc||jal||addi,
			ALUoperation=(addi||addu||sw||lw||lw2||lwe||jr?6'b000000:
							(subu||beq||bne?6'b000001:
							(ori||or1?6'b000011:
							(and1?6'b000010:
							(xor1?6'b000100:
							(sll?6'b000101:
							(srl?6'b000110:
							(sra?6'b000111:
							(lui?6'b001000:
						   (srav?6'b001001:
							(seh?6'b001010:
							(bnzalr?6'b001011:
							6'b001100)))))))))))),
			MemWrite=sw,
			MemtoReg=(lw2||lw||lwe?2'b01:(jal||jialc?2'b10:2'b00)),
			W=lui,
			Branch=beq,
			Branch2=bne,
			Branch3=bnzalr,
			J=ji?3'b101:(jialc?3'b100:(j?3'b011:(jal?3'b001:(jr?3'b010:3'b000))));
endmodule
