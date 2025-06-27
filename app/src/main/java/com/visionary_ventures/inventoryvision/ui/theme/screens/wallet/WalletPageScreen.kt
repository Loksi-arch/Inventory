package com.visionary_ventures.inventoryvision.ui.theme.screens.wallet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card';
import { ArrowUpCircle, ArrowDownCircle, Activity } from 'lucide-react';
import { useToast } from '@/hooks/use-toast';

@Composable
fun WalletPage_Screen(navController: NavHostController) {

    export default function WalletPage() {
        const { toast } = useToast();

        const handleDeposit = () => {
        toast({
            title: "Deposit Initiated",
            description: "Please follow the instructions to complete your deposit.",
        });
        // Implement actual deposit logic here
    };

        const handleWithdraw = () => {
        toast({
            title: "Withdrawal Requested",
            description: "Your withdrawal request is being processed.",
        });
        // Implement actual withdrawal logic here
    };

        return (
        <div className="space-y-8">
        <h1 className="text-3xl font-semibold">Wallet Management</h1>

        <Card className="card-common">
        <CardHeader>
        <CardTitle>Current Balance</CardTitle>
        <CardDescription>Your available funds in Ndovu Fund.</CardDescription>
        </CardHeader>
        <CardContent>
        <p className="text-5xl font-bold text-primary">Ksh 12,345.67</p> {/* Example Balance */}
        <p className="text-sm text-muted-foreground mt-2">Last updated: Just now</p>
        </CardContent>
        </Card>

        <div className="grid gap-6 md:grid-cols-2">
        <Card className="card-common">
        <CardHeader>
        <CardTitle>Deposit Funds</CardTitle>
        <CardDescription>Add funds to your Ndovu Fund wallet securely.</CardDescription>
        </CardHeader>
        <CardContent className="flex flex-col items-start gap-4">
        <p>Choose your preferred method to add funds to your account. Transactions are secure and processed quickly.</p>
        <Button size="lg" onClick={handleDeposit} className="w-full md:w-auto">
        <ArrowUpCircle className="mr-2 h-5 w-5" /> Deposit Now
        </Button>
        </CardContent>
        </Card>

        <Card className="card-common">
        <CardHeader>
        <CardTitle>Withdraw Funds</CardTitle>
        <CardDescription>Transfer funds from your wallet to your linked bank account.</CardDescription>
        </CardHeader>
        <CardContent className="flex flex-col items-start gap-4">
        <p>Easily withdraw your available balance. Withdrawals are typically processed within 24-48 hours.</p>
        <Button size="lg" variant="outline" onClick={handleWithdraw} className="w-full md:w-auto">
        <ArrowDownCircle className="mr-2 h-5 w-5" /> Withdraw Now
        </Button>
        </CardContent>
        </Card>
        </div>

        <Card className="card-common">
        <CardHeader>
        <CardTitle>Transaction History</CardTitle>
        <CardDescription>View your recent wallet activities.</CardDescription>
        </CardHeader>
        <CardContent>
            {/* Placeholder for transaction list */}
        <div className="flex items-center justify-center h-32 border-2 border-dashed rounded-lg">
        <div className="text-center text-muted-foreground">
        <Activity className="mx-auto h-8 w-8 mb-2" />
        <p>No recent transactions.</p>
        <p className="text-xs">Your deposits and withdrawals will appear here.</p>
        </div>
        </div>
        </CardContent>
        </Card>
        </div>
        );
    }


}

@Preview
@Composable
private fun WalletPrev() {

    WalletPage_Screen(rememberNavController())

}