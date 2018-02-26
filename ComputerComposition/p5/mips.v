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
	always@(posedge clk)begin
//			$display("!!!!%h",InstrD);
//			$display("!!!!%h %h %h %h",RegWriteD,RegWriteE,RegWriteM,RegWriteW);
//			$display("!!!!%h %h %h %h %h %h",RsD,RtD,RdE,WriteRegE,WriteRegM,WriteRegW);
//	   	$display("!!!!%h %h ||%h",SrcAE,SrcBE,ALUControlE);
//			$display("!!!!%h %h %h",ALUOutE,ALUOutM,ALUOutW);
//			$display("!!!!%h %h ",SrcAE,SrcBE);
//			$display("QWQ%h %h %h %h",Branch2D,!EqualD,A1,InstrD);
//			$display("!?????%h %h",((ForwardAD==2'b00)?RD1D:((ForwardAD==2'b01)?ResultW:((ForwardAD==2'b10)?ALUOutM:PCPlus8E))),(((ForwardBD==2'b00)?RD2D:((ForwardBD==2'b01)?ResultW:((ForwardBD==2'b10)?ALUOutM:PCPlus8E)))));
//			$display("!!!!%h ",EqualD);
  //  		$display("!!!!%h %h %h %h",PCPlus8D,PCPlus8E,PCPlus8M,PCPlus8W);
	//		$display("???%h %h",ForwardAD,ForwardAD);
	//		$display("?!!!!!!%h %h %h",RD1D,ResultW,ALUOutM,PCPlus8E);
	end	
	
	wire [31:0]InstrE,InstrM,InstrW;
	
	wire [31:0]PCPlus8D,PCPlus8E,PCPlus8M,PCPlus8W;
	assign  PCPlus8D=PCPlus4D+3'b100;
			//	PCPlus8D=PCPlus4D;
	wire [31:0]PC1,PCF,PCPlus4F;
	wire StallF;
	wire [1:0]PCSrcD;
	reg [31:0]PC;
	initial begin
		PC<=32'h3000;
	end
	wire [31:0]PCBranchJD,PCBranchD;
	assign PC1=((PCSrcD==2'b00)?PCPlus4F:((PCSrcD==2'b01)?PCBranchJD:(((ForwardBD==2'b00)?RD2D:((ForwardBD==2'b01)?ResultW:((ForwardBD==2'b10)?((JM!=3'b001&JM!=3'b100)?ALUOutM:PCPlus8M):PCPlus8E))))));
	assign PCF=PC;	
	always @(posedge clk)begin
		if(!StallF)begin
			if(reset)begin
				PC<=32'h3000;
			end			
			else PC<=PC1;
		end
	end
	
	
	
	assign PCPlus4F=PCF+3'b100;

	wire [31:0]InstrF;
	 im IM(
		.Addr(PCF),
		.Out(InstrF)
	 );
	 
	wire [31:0]InstrD,PCPlus4D; 
	wire StallD;
	IFID IFID (
		 .Clk(clk), 
		 .Reset(reset), 
		 .PCPlus4F(PCPlus4F), 
		 .InstrF(InstrF), 
		 .InstrD(InstrD), 
		 .PCPlus4D(PCPlus4D),
		 .StallD(StallD),
		 .PCSrcD(PCSrcD)
		 );
	 
	 wire BranchD,Branch2D,Branch3D;
	 wire [5:0]Op,Funct;
	 wire [1:0]REGDstD;
	 wire RegWriteD,MemWriteD,MemRead,W;
	 wire [1:0]MemtoRegD;
	 wire [5:0]ALUControlD;
	 wire [1:0]ALUSrcD;
	 wire [2:0]JD;
	 assign Op=InstrD[31:26],
			  Funct=InstrD[5:0];
	 controller CONTROLLER(
		.A(Op),
		.B(Funct),
		.REGDst(REGDstD),
		.ALUSrc(ALUSrcD),
		.RegWrite(RegWriteD),
		.ALUoperation(ALUControlD),
		.MemWrite(MemWriteD),
		.MemtoReg(MemtoRegD),
		.W(W),
		.Branch(BranchD),
		.Branch2(Branch2D),
		.Branch3(Branch3D),
		.J(JD)
	 );
	
	 wire [4:0]A1,A2,A3,WriteRegM;
	 wire [31:0]WD3,RD1D,RD2D,ResultW;
	 wire WE3;
	 assign A1=InstrD[25:21],
			  A2=InstrD[20:16],
			  A3=WriteRegW,
			  WData=((W==0)?ResultW:InstrD[15:0]<<16),
			  WD3=ResultW,
			  WE3=RegWriteW;
				
	 grf GRF(
		.Clk(clk),
		.Reset(reset),
		.RS1(A1),
		.RS2(A2),
		.RD(A3),
		.WData(WD3),
		.RegWrite(WE3),
		.RData1(RD1D),
		.RData2(RD2D),
		.J(JD)
	 );
	
	wire [31:0]ALUOutM; 
	wire EqualD,Equal2D;
	wire [1:0]ForwardBD,ForwardAD;
	assign EqualD=(
					((ForwardAD==2'b00)?RD1D:((ForwardAD==2'b01)?ResultW:((ForwardAD==2'b10)?((JM!=3'b001&JM!=3'b100)?ALUOutM:PCPlus8M):PCPlus8E)))
					==(((ForwardBD==2'b00)?RD2D:((ForwardBD==2'b01)?ResultW:((ForwardBD==2'b10)?((JM!=3'b001&JM!=3'b100)?ALUOutM:PCPlus8M):PCPlus8E))))
					),
			 Equal2D=(((ForwardAD==2'b00)?RD1D:((ForwardAD==2'b01)?ResultW:((ForwardAD==2'b10)?((JM!=3'b001&JM!=3'b100)?ALUOutM:PCPlus8M):PCPlus8E)))
						==32'b0
						);
	assign PCSrcD=((BranchD&EqualD)|(Branch2D&(!EqualD))|(JD!=3'b000))?2'b01:((Branch3D&(!Equal2D))?2'b10:2'b00);

	wire [31:0]SignImmD;
	EXT ext (
	 .Instr(InstrD),
    .SignImmD(SignImmD)
    );
   wire [1:0]ForwardJ;
	assign PCBranchD=PCPlus4D+({{16{SignImmD[15]}},SignImmD[15:0]}<<2); 
	assign PCBranchJD=((JD==3'b000)?PCBranchD:
							((JD==3'b001||JD==3'b011)?{PCPlus4D[31:28],InstrD[25:0],2'b0}:
							((JD==3'b100|JD==3'b101)?((((ForwardBD==2'b00)?RD2D:((ForwardBD==2'b01)?ResultW:((ForwardBD==2'b10)?((JM!=3'b001&JM!=3'b100)?ALUOutM:PCPlus8M):PCPlus8E))))+SignImmD):
							((ForwardJ==2'b01)?ResultW:
							((ForwardJ==2'b10)?((JM!=3'b001&JM!=3'b100)?ALUOutM:PCPlus8M):RD1D)))));

//////////////////////////////////////////////	 
	wire [4:0]RsD,RtD,RdD,RdE,SD,SE;
	wire [4:0]RsE,RtE;
	wire [31:0]RD1E,RD2E;
	wire FlushE;
	wire [31:0]SignImmE;
	wire [1:0]REGDstE;
	wire RegWriteE,MemWriteE;
	wire [1:0]MemtoRegE;
	wire [5:0]ALUControlE;
	wire [1:0]ALUSrcE;
	wire [2:0]JE,JM;
	assign RsD=InstrD[25:21],
			 RtD=InstrD[20:16],
			 RdD=InstrD[15:11],
			 SD=InstrD[10:6];
	IDEX IDEX (
	.InstrD(InstrD),
	.InstrE(InstrE),
		 .JD(JD),
		 .JE(JE),
		 .RD1D(RD1D), 
		 .RD2D(RD2D), 
		 .SignImmD(SignImmD), 
		 .RsD(RsD), 
		 .RtD(RtD), 
		 .RdD(RdD), 
		 .SD(SD),
		 .Clk(clk), 
		 .Reset(reset),
		 .FlushE(FlushE),		 
		 .RegWriteD(RegWriteD), 
		 .MemtoRegD(MemtoRegD), 
		 .MemWriteD(MemWriteD), 
		 .ALUoperationD(ALUControlD), 
		 .ALUSrcD(ALUSrcD), 
		 .REGDstD(REGDstD), 
		 .RD1E(RD1E), 
		 .RD2E(RD2E), 
		 .SignImmE(SignImmE), 
		 .RsE(RsE), 
		 .RtE(RtE), 
		 .RdE(RdE), 
		 .SE(SE),
		 .REGDstE(REGDstE), 
		 .ALUSrcE(ALUSrcE), 
		 .RegWriteE(RegWriteE), 
		 .ALUoperationE(ALUControlE), 
		 .MemWriteE(MemWriteE), 
		 .MemtoRegE(MemtoRegE),
		 
		 .PCPlus8D(PCPlus8D),
		 .PCPlus8E(PCPlus8E)
		 );
	wire [4:0]WriteRegE;
	assign WriteRegE=((REGDstE==2'b00)?RtE:((REGDstE==2'b01)?RdE:31));

	wire [31:0]SrcAE,SrcBE,WriteDataE,ALUOutE;	
	wire [1:0]ForwardBE,ForwardAE;
	assign SrcAE=((ForwardAE==2'b00)?RD1E:((ForwardAE==2'b01)?ResultW:((JM!=3'b001&JM!=3'b100)?ALUOutM:PCPlus8M))),
			 WriteDataE=((ForwardBE==2'b00)?RD2E:((ForwardBE==2'b01)?ResultW:((JM!=3'b001&JM!=3'b100)?ALUOutM:PCPlus8M))),
			 SrcBE=((ALUSrcE==2'b10)?SignImmE:((ALUSrcE==2'b00)?WriteDataE:PCPlus8E));
	

	 alu ALU(
		.A(SrcAE),
		.B(SrcBE),
		.S(SE),
		.ALUOp(ALUControlE),
		.C(ALUOutE)
	 );

	wire RegWriteM,MemWriteM;
	wire [1:0]MemtoRegM;	
	wire [31:0]WriteDataM;
	EXMEM EXMEM (
	.InstrE(InstrE),
	.InstrM(InstrM),
		 .JE(JE),
	    .JM(JM),
		 .ALUOutE(ALUOutE), 
		 .WriteDataE(WriteDataE), 
		 .WriteRegE(WriteRegE), 
		 .Clk(clk), 
		 .Reset(reset), 
		 .RegWriteE(RegWriteE), 
		 .MemtoRegE(MemtoRegE), 
		 .MemWriteE(MemWriteE), 
		 .ALUOutM(ALUOutM), 
		 .WriteDataM(WriteDataM), 
		 .WriteRegM(WriteRegM), 
		 .RegWriteM(RegWriteM), 
		 .MemtoRegM(MemtoRegM), 
		 .MemWriteM(MemWriteM),
		 
		 .PCPlus8E(PCPlus8E),
		 .PCPlus8M(PCPlus8M)
		 );
	
	wire ForwardBM;
	wire [31:0]ReadDataM;
	dm DM(
		.ALUOutM(ALUOutM),
		.Data(ForwardBM==1?ResultW:WriteDataM),
		.Reset(reset),
		.MemWrite(MemWriteM),
		.Out(ReadDataM),
		.Clk(clk)
	);

	wire [1:0]MemtoRegW;	
	wire [31:0]ReadDataW,ALUOutW;
	wire [4:0]WriteRegW;
	MEMWB MEMWB (
		 .ReadDataM(ReadDataM), 
		 .ALUOutM(ALUOutM), 
		 .WriteRegM(WriteRegM), 
		 .RegWriteM(RegWriteM), 
		 .MemtoRegM(MemtoRegM), 
		 .Clk(clk), 
		 .Reset(reset), 
		 .RegWriteW(RegWriteW), 
		 .MemtoRegW(MemtoRegW), 
		 .ReadDataW(ReadDataW), 
		 .ALUOutW(ALUOutW), 
		 .WriteRegW(WriteRegW),
		 
		 .PCPlus8M(PCPlus8M),
		 .PCPlus8W(PCPlus8W)
		 );
	assign ResultW=((MemtoRegW==2'b01)?ReadDataW:((MemtoRegW==2'b00)?ALUOutW:PCPlus8W));
	
	hazard HAZARD (

    .WriteRegW(WriteRegW), 
    .WriteRegM(WriteRegM), 
    .WriteRegE(WriteRegE), 
    .RegWriteW(RegWriteW),
    .RegWriteD(RegWriteD),	 
    .RegWriteM(RegWriteM), 
    .MemtoRegM(MemtoRegM), 
    .RegWriteE(RegWriteE), 
    .MemtoRegE(MemtoRegE), 
    .BranchD(BranchD),
	 .Branch2D(Branch2D),
	 .Branch3D(Branch3D),
    .JD(JD),	 
 
 	 .rdE(RdE),
 
    .rsE(RsE), 
    .rtE(RtE), 
    .rsD(RsD), 
    .rtD(RtD), 
    .ForwardBE(ForwardBE), 
    .ForwardAE(ForwardAE), 
    .FlushE(FlushE), 
    .ForwardBD(ForwardBD), 
    .ForwardAD(ForwardAD),
    .ForwardBM(ForwardBM), 
    .StallD(StallD), 
    .StallF(StallF),
	 .ForwardJ(ForwardJ)
    );
endmodule
