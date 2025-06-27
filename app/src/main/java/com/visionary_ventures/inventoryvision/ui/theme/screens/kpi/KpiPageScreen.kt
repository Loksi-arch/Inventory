package com.visionary_ventures.inventoryvision.ui.theme.screens.kpi

import { KpiCard } from './components/kpi-card';
import OverviewChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

from './components/overview-chart';
import { MOCK_KPIS } from '@/lib/constants';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { CreditCard, TrendingUp, Users } from 'lucide-react';

{ LucideIcon } from 'lucide-react';

@Composable
fun KPIPAGE_Screen(navController: NavHostController) {

    const iconMap: Record<string, LucideIcon> = {
            CreditCard,
            TrendingUp,
            Users,
    };

    export default function DashboardPage() {
        return (
        <div className="flex flex-col gap-6">
        <h1 className="text-3xl font-semibold">Dashboard</h1>

        <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
        {MOCK_KPIS.map((kpi) => (
            <KpiCard
                key={kpi.title}
            title={kpi.title}
            value={kpi.value}
            change={kpi.change}
            changeType={kpi.changeType}
            icon={kpi.icon === 'DollarSign' ? '💵' : (kpi.icon && iconMap[kpi.icon] ? iconMap[kpi.icon] : undefined)}
            />
            ))}
        </div>

        <Card className="card-common">
        <CardHeader>
        <CardTitle>Performance Overview</CardTitle>
        <CardDescription>Track your key metrics over time.</CardDescription>
        </CardHeader>
        <CardContent>
        <OverviewChart />
        </CardContent>
        </Card>
        </div>
        );
    }
}

@Preview
@Composable
private fun PagePrev() {

    KPIPAGE_Screen(rememberNavController())

}
