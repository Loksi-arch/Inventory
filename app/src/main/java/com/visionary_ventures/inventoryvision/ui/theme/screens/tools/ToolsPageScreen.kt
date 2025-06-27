package com.visionary_ventures.inventoryvision.ui.theme.screens.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import InventoryTracker
import androidx.navigation.NavHostController

from './components/inventory-tracker';
import ReportGenerator from './components/report-generator';

@Composable
fun ToolsPage_Screen(navController: NavHostController) {

    export default function ToolsPage() {
        return (
        <div className="space-y-8">
        <h1 className="text-3xl font-semibold">Intelligent Tools</h1>

        <Card className="card-common">
        <CardHeader>
        <CardTitle>Inventory Tracking</CardTitle>
        <CardDescription>Monitor stock levels and manage reorders efficiently.</CardDescription>
        </CardHeader>
        <CardContent>
        <InventoryTracker />
        </CardContent>
        </Card>

        <Card className="card-common">
        <CardHeader>
        <CardTitle>Automated Report Generation</CardTitle>
        <CardDescription>Schedule and customize reports for your business insights.</CardDescription>
        </CardHeader>
        <CardContent>
        <ReportGenerator />
        </CardContent>
        </Card>
        </div>
        );
    }

}

@Preview
@Composable
private fun ToolsPrev() {

    ToolsPage_Screen(rememberNavController())

}