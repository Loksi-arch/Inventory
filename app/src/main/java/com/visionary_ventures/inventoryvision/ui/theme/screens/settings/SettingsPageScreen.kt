package com.visionary_ventures.inventoryvision.ui.theme.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import { useState } from 'react';
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Switch } from '@/components/ui/switch';
import { Label } from '@/components/ui/label';
import { MOCK_SETTINGS_PAGE_CONFIG } from '@/lib/constants';
import type { SettingsItem } from '@/types';
import { ChevronRight, Edit, MoreVertical, Camera, Phone as PhoneIcon, Languages, Smartphone, Laptop2, Power, LogOut } from 'lucide-react';
import Link from 'next/link';
@Composable
fun SettingsPage_Screen(navController: NavHostController) {

    export default function SettingsPage() {
        const { userName, userStatus, userAvatarUrl, userPhoneNumber, settingItems } = MOCK_SETTINGS_PAGE_CONFIG;
        const [selectedLanguage, setSelectedLanguage] = useState('en');
        const [isPowerSavingEnabled, setIsPowerSavingEnabled] = useState(false);

        const mockDevices = [
            { id: '1', name: 'NdovuApp on iPhone 15', icon: Smartphone, lastSeen: 'Online' },
            { id: '2', name: 'Chrome on MacBook Air', icon: Laptop2, lastSeen: 'Active 2 hours ago' },
        ];

        return (
        <div className="space-y-6">
        <div className="flex justify-between items-center">
        <h1 className="text-3xl font-semibold">Settings</h1>
        <div className="flex items-center gap-2">
        <Button variant="ghost" size="icon">
        <Edit className="h-5 w-5" />
        <span className="sr-only">Edit</span>
        </Button>
        <Button variant="ghost" size="icon">
        <MoreVertical className="h-5 w-5" />
        <span className="sr-only">More options</span>
        </Button>
        </div>
        </div>

        {/* User Info Card */}
        <Card className="card-common overflow-hidden">
        <CardContent className="p-6 flex flex-col items-center text-center">
        <div className="relative mb-4">
        <Avatar className="h-32 w-32 border-4 border-background shadow-lg">
        <AvatarImage src={userAvatarUrl} alt={userName} data-ai-hint="user avatar" />
        <AvatarFallback>{userName.charAt(0).toUpperCase()}</AvatarFallback>
        </Avatar>
        <Button
        variant="default"
        size="icon"
        className="absolute bottom-2 right-2 rounded-full h-10 w-10 bg-primary text-primary-foreground hover:bg-primary/90"
        >
        <Camera className="h-5 w-5" />
        <span className="sr-only">Change photo</span>
        </Button>
        </div>
        <h2 className="text-2xl font-semibold">{userName}</h2>
        <p className="text-sm text-muted-foreground">{userStatus}</p>
        </CardContent>
        </Card>

        {/* Phone Number Card */}
        <Card className="card-common">
        <CardContent className="p-4">
        <div className="flex items-center justify-between">
        <div className="flex items-center gap-4">
        <PhoneIcon className="h-6 w-6 text-muted-foreground" />
        <div>
        <p className="font-medium">{userPhoneNumber}</p>
        <p className="text-sm text-muted-foreground">Phone</p>
        </div>
        </div>
        <ChevronRight className="h-5 w-5 text-muted-foreground" />
        </div>
        </CardContent>
        </Card>

        {/* Language Settings Card */}
        <Card className="card-common">
        <CardHeader>
        <CardTitle className="flex items-center gap-2">
        <Languages className="h-6 w-6 text-primary" />
        Language
        </CardTitle>
        <CardDescription>Choose your preferred application language.</CardDescription>
        </CardHeader>
        <CardContent>
        <Select value={selectedLanguage} onValueChange={setSelectedLanguage}>
        <SelectTrigger className="w-full md:w-[240px]">
        <SelectValue placeholder="Select language" />
        </SelectTrigger>
        <SelectContent>
        <SelectItem value="en">English</SelectItem>
        <SelectItem value="sw">Swahili</SelectItem>
        <SelectItem value="fr">Français</SelectItem>
        </SelectContent>
        </Select>
        </CardContent>
        </Card>

        {/* Connected Devices Card */}
        <Card className="card-common">
        <CardHeader>
        <CardTitle className="flex items-center gap-2">
        <Smartphone className="h-6 w-6 text-primary" />
        Connected Devices
        </CardTitle>
        <CardDescription>Manage devices linked to your account.</CardDescription>
        </CardHeader>
        <CardContent className="space-y-4">
        {mockDevices.map(device => (
            <div key={device.id} className="flex items-center justify-between p-3 border rounded-lg bg-muted/20">
            <div className="flex items-center gap-3">
            <device.icon className="h-5 w-5 text-muted-foreground" />
            <div>
            <p className="font-medium">{device.name}</p>
            <p className="text-xs text-muted-foreground">{device.lastSeen}</p>
            </div>
            </div>
            <Button variant="ghost" size="sm">
            <LogOut className="h-4 w-4 mr-2" /> Disconnect
            </Button>
            </div>
            ))}
        <Button variant="outline" className="w-full mt-2">Manage All Devices</Button>
        </CardContent>
        </Card>

        {/* Power Saving Card */}
        <Card className="card-common">
        <CardHeader>
        <CardTitle className="flex items-center gap-2">
        <Power className="h-6 w-6 text-primary" />
        Power Saving
        </CardTitle>
        <CardDescription>Optimize application performance and battery usage.</CardDescription>
        </CardHeader>
        <CardContent>
        <div className="flex items-center justify-between">
        <Label htmlFor="power-saving-switch" className="text-base">Enable Power Saving Mode</Label>
        <Switch
        id="power-saving-switch"
        checked={isPowerSavingEnabled}
        onCheckedChange={setIsPowerSavingEnabled}
        />
        </div>
        <p className="text-xs text-muted-foreground mt-2">
        Reduces background activity and animations to save power.
        </p>
        </CardContent>
        </Card>

        {/* Settings List (Existing) */}
        <Card className="card-common">
        <CardHeader>
        <CardTitle>More Settings</CardTitle>
        <CardDescription>Access other application settings.</CardDescription>
        </CardHeader>
        <CardContent className="p-0">
        <ul className="divide-y divide-border">
        {settingItems.map((item: SettingsItem) => (
            <li key={item.id}>
            <Link href={`/settings/${item.id}`} legacyBehavior>
            <a className="flex items-center justify-between p-4 hover:bg-muted/50 transition-colors">
            <div className="flex items-center gap-4">
            <item.icon className="h-6 w-6 text-muted-foreground" />
            <span className="text-base">{item.label}</span>
            </div>
            <ChevronRight className="h-5 w-5 text-muted-foreground" />
            </a>
            </Link>
            </li>
            ))}
        </ul>
        </CardContent>
        </Card>
        </div>
        );
    }

}

@Preview
@Composable
private fun SettingsPrev() {

    SettingsPage_Screen(rememberNavController())

}