package com.visionary_ventures.inventoryvision.ui.theme.screens.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Select, SelectContent, SelectGroup, SelectItem, SelectLabel, SelectTrigger, SelectValue } from '@/components/ui/select';
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group';
import { Checkbox } from '@/components/ui/checkbox';
import { Label } from '@/components/ui/label';
import { Input } from '@/components/ui/input';
import { MOCK_REPORT_CONFIG } from '@/lib/constants';
import type { ReportConfig } from '@/types';
import { Download, Mail, Settings2, CalendarClock, Save } from 'lucide-react';
import { format } from 'date-fns';

@Composable
fun ReportGenerator_Screen(navController: NavHostController) {

    const reportTypes = [
        { value: 'sales_summary', label: 'Sales Summary' },
        { value: 'inventory_status', label: 'Inventory Status' },
        { value: 'customer_activity', label: 'Customer Activity' },
        { value: 'profit_loss', label: 'Profit & Loss Statement' },
    ];

    const scheduleOptions = [
        { value: 'daily', label: 'Daily' },
        { value: 'weekly', label: 'Weekly' },
        { value: 'monthly', label: 'Monthly' },
        { value: 'custom', label: 'Custom (Cron)' },
    ];

    const deliveryOptions = [
        { id: 'email', label: 'Email Notification', icon: Mail },
        { id: 'download', label: 'Automatic Download', icon: Download },
        { id: 'dashboard', label: 'Save to Dashboard', icon: Settings2 },
    ];

    export default function ReportGenerator() {
        const [config, setConfig] = useState<ReportConfig>(MOCK_REPORT_CONFIG);

        const handleInputChange = (field: keyof ReportConfig, value: any) => {
        setConfig(prev => ({ ...prev, [field]: value }));
    };

        const handleDeliveryOptionChange = (optionId: string, checked: boolean) => {
        setConfig(prev => {
            const currentOptions = prev.deliveryOptions || [];
            if (checked) {
                return { ...prev, deliveryOptions: [...currentOptions, optionId as any] };
            } else {
                return { ...prev, deliveryOptions: currentOptions.filter(opt => opt !== optionId) };
            }
        });
    };

        const handleGenerateReport = () => {
        console.log("Generating report with config:", config);
        // Add toast: Report generating...
    };

        const handleSaveSchedule = () => {
        console.log("Saving schedule with config:", config);
        // Add toast: Schedule saved!
    };

        return (
        <div className="space-y-8">
        <div className="grid md:grid-cols-2 gap-6">
        <div>
        <Label htmlFor="reportType" className="text-base font-medium">Report Type</Label>
        <Select
        value={config.reportType}
        onValueChange={(value) => handleInputChange('reportType', value)}
        >
        <SelectTrigger id="reportType" className="mt-1 h-11">
        <SelectValue placeholder="Select a report type" />
        </SelectTrigger>
        <SelectContent>
        <SelectGroup>
        <SelectLabel>Available Reports</SelectLabel>
            {reportTypes.map(rt => (
                <SelectItem key={rt.value} value={rt.value}>{rt.label}</SelectItem>
                ))}
        </SelectGroup>
        </SelectContent>
        </Select>
        </div>
        <div>
        <Label className="text-base font-medium block mb-2">Schedule</Label>
        <RadioGroup
        value={config.schedule}
        onValueChange={(value) => handleInputChange('schedule', value)}
        className="flex space-x-4 mt-1"
        >
        {scheduleOptions.map(so => (
            <div key={so.value} className="flex items-center space-x-2">
            <RadioGroupItem value={so.value} id={`schedule-${so.value}`} />
            <Label htmlFor={`schedule-${so.value}`} className="font-normal">{so.label}</Label>
            </div>
            ))}
        </RadioGroup>
        {config.schedule === 'custom' && (
            <Input
            placeholder="Enter CRON expression (e.g., 0 0 * * MON)"
            className="mt-2 h-11"
            value={config.customCron || ''}
            onChange={(e) => handleInputChange('customCron', e.target.value)}
            />
            )}
        </div>
        </div>

        <div>
        <Label className="text-base font-medium">Delivery Options</Label>
        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-4 mt-2">
        {deliveryOptions.map(opt => (
            <div key={opt.id} className="flex items-center space-x-3 p-3 border rounded-lg bg-secondary/30">
            <Checkbox
            id={`delivery-${opt.id}`}
            checked={config.deliveryOptions.includes(opt.id as any)}
            onCheckedChange={(checked) => handleDeliveryOptionChange(opt.id, !!checked)}
            />
            <opt.icon className="h-5 w-5 text-primary" />
            <Label htmlFor={`delivery-${opt.id}`} className="font-normal text-sm">
            {opt.label}
            </Label>
            </div>
            ))}
        </div>
        </div>

        {config.lastGenerated && (
            <p className="text-sm text-muted-foreground">
            Last report generated on: {format(new Date(config.lastGenerated), "PPP p")}
            </p>
            )}

        <div className="flex flex-col sm:flex-row justify-end gap-4 pt-4">
        <Button variant="outline" size="lg" onClick={handleGenerateReport}>
        <Download className="mr-2 h-5 w-5" /> Generate Report Now
        </Button>
        <Button size="lg" onClick={handleSaveSchedule}>
        <Save className="mr-2 h-5 w-5" /> Save Schedule
        </Button>
        </div>
        </div>
        );
    }

}

@Preview
@Composable
private fun ReportPrev() {



}