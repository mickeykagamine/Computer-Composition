`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    10:57:33 12/07/2016 
// Design Name: 
// Module Name:    EXMEM 
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
module EXMEM(
input [31:0]InstrE,
output [31:0]InstrM,
	input [2:0]JE,
	output [2:0]JM,
	input [31:0]ALUOutE,
	input [31:0]WriteDataE,
	input [4:0]WriteRegE,
	
	input [31:0]PCPlus8E,
	
	input Clk,
	input Reset,
	
	input RegWriteE,
	input [1:0]MemtoRegE,
	input MemWriteE,
	
	output [31:0]PCPlus8M,
	
	output [31:0]ALUOutM,
	output [31:0]WriteDataM,
	output [4:0]WriteRegM,

	output RegWriteM,
	output [1:0]MemtoRegM,
	output MemWriteM
    );
	 initial begin
			aluout<=32'b0;
			writedata<=32'b0;
			writereg<=5'b0;

			pcplus8<=32'b0;
instr<=32'b0;			
			regwrite<=0;
			memwrite<=0;
			memtoreg<=0;
	 end
	 reg [2:0]j;
	 reg [31:0]pcplus8,instr;
	 reg [31:0]aluout,writedata;
	 reg [4:0]writereg;
	 reg regwrite,memwrite;
	 reg [1:0]memtoreg;
	 assign ALUOutM=aluout,
			  WriteDataM=writedata,
			  WriteRegM=writereg,
InstrM=instr,			  
			  PCPlus8M=pcplus8,
			  JM=j,
			  RegWriteM=regwrite,
			  MemWriteM=memwrite,
	 		  MemtoRegM=memtoreg;
			  
	 always @(posedge Clk)begin
		if(Reset)begin
			aluout<=32'b0;
			writedata<=32'b0;
			writereg<=5'b0;
instr<=32'b0;
			pcplus8<=32'b0;
			j<=0;
			regwrite<=0;
			memwrite<=0;
			memtoreg<=0;
	
		end
		else begin
			aluout<=ALUOutE;
			writedata<=WriteDataE;
			writereg<=WriteRegE;
instr<=InstrE;			
			pcplus8<=PCPlus8E;
			j<=JE;			
			regwrite<=RegWriteE;
			memwrite<=MemWriteE;
			memtoreg<=MemtoRegE;
		end
	 end

endmodule
