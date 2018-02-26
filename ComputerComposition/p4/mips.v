`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:47:05 11/30/2016 
// Design Name: 
// Module Name:    mips 
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
module mips(
	input clk,
	input reset
    );
	
	wire [31:0]Addr;
	wire [31:0]struction,C,PC4;
//	always@(posedge clk)begin
//			$display("!!!!%h",struction);
//	end
	wire [1:0]J;
	wire Branch,Branch2,Zero,Zero2;
	 IF if1(
		.Clk(clk),
		.Branch(Branch),
		.Branch2(Branch2),
		.Zero(Zero),
		.Zero2(Zero2),
		.Reset(reset),
		.structionold(struction),
		.Addr(Addr),
		.J(J),
		.C(C),
		.PC4(PC4)
	 );
	 
	 im IM(
		.Addr(Addr),
		.Out(struction)
	 );
	 wire [5:0]A,B,rt;
	 wire REGDst,RegWrite,MemWrite,MemRead,MemtoReg,W,bgezal;
	 wire [2:0]ALUoperation;
	 wire [1:0]ALUSrc;
	 assign A=struction[31:26],
			B=struction[5:0],
			rt=struction[20:16];
	 controller CONTROLLER(
		.A(A),
		.B(B),
		.REGDst(REGDst),
		.ALUSrc(ALUSrc),
		.RegWrite(RegWrite),
		.ALUoperation(ALUoperation),
		.MemWrite(MemWrite),
		.MemRead(MemRead),
		.MemtoReg(MemtoReg),
		.W(W),
		.J(J),
		.bgezal(bgezal),
		.Branch(Branch),
		.Branch2(Branch2),
		.rt(rt)
	 );
	
	 wire [4:0]RS1,RS2,RD;
	 wire [31:0]WData,RData1,RData2,Out3;
	 assign RS1=struction[25:21],
				RS2=struction[20:16],
				RD=((REGDst==0)?RS2:struction[15:11]),
				WData=((W==0)?Out3:struction[15:0]<<16);
				
	 grf GRF(
		.PC4(PC4),
		.J(J),
		.bgezal(bgezal),
		.Clk(clk),
		.RS1(RS1),
		.RS2(RS2),
		.RD(RD),
		.WData(WData),
		.RegWrite(RegWrite),
		.Reset(reset),
		.RData1(RData1),
		.RData2(RData2),
		.struction(struction)
	 );
	 
	 wire [31:0]A1,B1;
	 assign A1=RData1,
				B1=((ALUSrc==3'b00)?RData2:((ALUSrc==3'b01)?{{16{struction[15]}},struction[15:0]}:{16'b0,struction[15:0]}));
	 alu ALU(
		.A(A1),
		.B(B1),
		.ALUOp(ALUoperation),
		.C(C),
		.Zero(Zero),
		.Zero2(Zero2),
		.Clk(clk)
	 );

	wire [31:0]Data,Out2;
	wire [9:0]Addr2;
	dm DM(
		.Addr(Addr2),
		.Data(Data),
		.MemRead(MemRead),
		.Reset(reset),
		.MemWrite(MemWrite),
		.Out(Out2),
		.Clk(clk)
	);
	assign Addr2=C[11:2], 
			Data=RData2,
			Out3=((MemtoReg==0)?C:Out2);
endmodule
